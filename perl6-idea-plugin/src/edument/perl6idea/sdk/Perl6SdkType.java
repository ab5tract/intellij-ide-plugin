package edument.perl6idea.sdk;

import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.*;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.testFramework.LightVirtualFile;
import com.intellij.util.containers.ContainerUtil;
import edument.perl6idea.Perl6Icons;
import edument.perl6idea.psi.Perl6File;
import edument.perl6idea.psi.Perl6PackageDecl;
import edument.perl6idea.psi.external.ExternalPerl6File;
import edument.perl6idea.psi.symbols.*;
import edument.perl6idea.utils.Perl6CommandLine;
import edument.perl6idea.utils.Perl6Utils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Perl6SdkType extends SdkType {
    private static final String NAME = "Perl 6 SDK";
    public static final String SETTING_FILE_NAME = "SETTINGS.pm6";
    private static final Set<String> BINARY_NAMES = new HashSet<>();
    private static Logger LOG = Logger.getInstance(Perl6SdkType.class);
    private Map<String, String> moarBuildConfig;

    // Symbol caches
    private Map<String, JSONArray> useNameSymbolCache = new ConcurrentHashMap<>();
    private Map<String, Perl6File> useNameFileCache = new ConcurrentHashMap<>();

    private Map<String, JSONArray> needNameSymbolCache = new ConcurrentHashMap<>();
    private Map<String, Perl6File> needNameFileCache = new ConcurrentHashMap<>();

    private JSONArray settingJson;
    private Perl6File setting;
    private final AtomicBoolean mySettingsStarted = new AtomicBoolean(false);
    private Set<String> myPackageStarted = ContainerUtil.newConcurrentSet();

    static {
        BINARY_NAMES.add("perl6");
        BINARY_NAMES.add("perl6.bat");
        BINARY_NAMES.add("perl6.exe");
        BINARY_NAMES.add("raku");
        BINARY_NAMES.add("raku.exe");
        BINARY_NAMES.add("rakudo");
        BINARY_NAMES.add("rakudo.exe");
    }

    private Perl6SdkType() {
        super(NAME);
    }

    public static Perl6SdkType getInstance() {
        return SdkType.findInstance(Perl6SdkType.class);
    }

    @NotNull
    @Override
    public Icon getIcon() {
        return Perl6Icons.CAMELIA;
    }

    @NotNull
    @Override
    public Icon getIconForAddAction() {
        return getIcon();
    }

    @Nullable
    @Override
    public String suggestHomePath() {
        // There might be different installations, such as package,
        // rakudobrew, p6env etc, so for now just return the first one
        // from PATH we can find
        return findPerl6SdkDirInPath();
    }

    @Nullable
    private static String findPerl6SdkDirInPath() {
        final String path = System.getenv("PATH");
        for (String root : path.split(File.pathSeparator)) {
            final String file = findPerl6InSdkHome(root);
            if (file != null) return root;
        }
        return null;
    }

    @Nullable
    public static String findPerl6InSdkHome(String home) {
        for (String command : BINARY_NAMES) {
            final File file = new File(home, command);
            if (file.exists() && file.isFile() && file.canExecute()) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    @Nullable
    public static String getSdkHomeByProject(@NotNull Project project) {
        Sdk sdk = ProjectRootManager.getInstance(project).getProjectSdk();
        return sdk != null && sdk.getSdkType() instanceof Perl6SdkType
               ? sdk.getHomePath()
               : null;
    }

    @Override
    public boolean isValidSdkHome(@NotNull String path) {
        for (String exe : BINARY_NAMES) {
            File file = Paths.get(path, exe).toFile();
            if (file.exists() && file.isFile() && file.canExecute())
                return true;
        }
        return false;
    }

    @Nullable
    @Override
    public AdditionalDataConfigurable createAdditionalDataConfigurable(@NotNull SdkModel model, @NotNull SdkModificator modificator) {
        return null;
    }

    @Override
    public void saveAdditionalData(@NotNull SdkAdditionalData data, @NotNull Element additional) {
    }

    @Nullable
    @Override
    public String getVersionString(@NotNull String path) {
        String binPath = findPerl6InSdkHome(path);
        if (binPath == null)
            return null;
        String[] command = {binPath, "-e", "say $*PERL.compiler.version"};
        String line = null;
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        try {
            Process process = processBuilder.start();
            try (
                InputStreamReader in = new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader processOutputReader = new BufferedReader(in)) {
                line = processOutputReader.readLine();
                if (process.waitFor() != 0)
                    return null;
            }
        } catch (IOException|InterruptedException e) {
            LOG.warn(e);
        }
        return line;
    }

    @NotNull
    @Override
    public String suggestSdkName(@Nullable String currentSdkName, @NotNull String sdkHome) {
        String version = getVersionString(sdkHome);
        if (version == null) {
            return "Unknown at " + sdkHome;
        }
        return "Perl 6 " + version;
    }

    @NotNull
    @Override
    public String getPresentableName() {
        return "Perl 6 SDK";
    }

    @Nullable
    public Map<String, String> getMoarBuildConfiguration(Project project) {
        if (moarBuildConfig != null)
            return moarBuildConfig;
        List<String> subs;

        try {
            Perl6CommandLine cmd = new Perl6CommandLine(project);
            cmd.setWorkDirectory(System.getProperty("java.io.tmpdir"));
            cmd.addParameter("--show-config");
            subs = cmd.executeAndRead();
            Map<String, String> buildConfig = new TreeMap<>();

            for (String line : subs) {
                int equalsPosition = line.indexOf('=');
                if (equalsPosition > 0) {
                    String key = line.substring(0, equalsPosition);
                    String value = line.substring(equalsPosition + 1);
                    buildConfig.put(key, value);
                }
            }
            moarBuildConfig = buildConfig;
        } catch (ExecutionException e) {
            LOG.warn(e);
            return null;
        }

        return moarBuildConfig;
    }

    public Perl6File getCoreSettingFile(Project project) {
        if (setting != null)
            return setting;
        if (settingJson != null)
            return setting = makeSettingSymbols(project, settingJson);

        File coreSymbols = Perl6Utils.getResourceAsFile("symbols/perl6-core-symbols.p6");
        File coreDocs = Perl6Utils.getResourceAsFile("docs/core.json");
        String perl6path = getSdkHomeByProject(project);


        if (perl6path == null || coreSymbols == null || coreDocs == null) {
            String errorMessage = perl6path == null
                                  ? "getCoreSettingFile is called without Perl 6 SDK set, using fallback"
                                  : coreSymbols == null
                                    ? "getCoreSettingFile is called with corrupted resources bundle, using fallback"
                                    : "getCoreSettingFile is called with corrupted resources bundle";
            LOG.warn(errorMessage);
            return getFallback(project);
        }

        try {
            if (mySettingsStarted.compareAndSet(false, true)) {
                Perl6CommandLine cmd = new Perl6CommandLine(project);
                cmd.setWorkDirectory(System.getProperty("java.io.tmpdir"));
                cmd.addParameter(coreSymbols.getAbsolutePath());
                cmd.addParameter(coreDocs.getAbsolutePath());
                ApplicationManager.getApplication().executeOnPooledThread(() -> {
                    try {
                        String settingLines = String.join("\n", cmd.executeAndRead());
                        if (settingLines.isEmpty()) {
                            LOG.warn("getCoreSettingFile got no symbols from Perl 6, using fallback");
                            getFallback(project);
                        }
                        else {
                            setting = makeSettingSymbols(project, settingLines);
                        }
                        triggerCodeAnalysis(project);
                    } catch (AssertionError e) {
                        // If the project was already disposed, do not die in a background thread
                    }
                });
            }
            return new ExternalPerl6File(project, new LightVirtualFile("DUMMY"));
        } catch (ExecutionException e) {
            LOG.error(e);
            return getFallback(project);
        }
    }

    private static void triggerCodeAnalysis(Project project) {
        ApplicationManager.getApplication().runReadAction(() -> {
            if (project.isDisposed()) return;
            FileEditor[] editors = FileEditorManager.getInstance(project).getSelectedEditors();
            for (FileEditor editor : editors) {
                if (editor != null && editor.getFile() != null) {
                    PsiFile psiFile = PsiManager.getInstance(project).findFile(editor.getFile());
                    if (psiFile != null)
                        DaemonCodeAnalyzer.getInstance(project).restart(psiFile);
                }
            }
        });
    }

    private Perl6File getFallback(Project project) {
        File fallback = Perl6Utils.getResourceAsFile("symbols/CORE.fallback");
        if (fallback == null) {
            LOG.error("getCoreSettingFile is called with corrupted resources bundle");
            return new ExternalPerl6File(project, new LightVirtualFile(SETTING_FILE_NAME));
        }

        try {
            return setting = makeSettingSymbols(project, new String(Files.readAllBytes(fallback.toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOG.error(e);
            return new ExternalPerl6File(project, new LightVirtualFile(SETTING_FILE_NAME));
        }
    }

    private Perl6File makeSettingSymbols(Project project, String json) {
        try {
            settingJson = new JSONArray(json);
            return makeSettingSymbols(project, settingJson);
        } catch (JSONException e) {
            Logger.getInstance(Perl6SdkType.class).warn(e);
        }
        return new ExternalPerl6File(project, new LightVirtualFile(SETTING_FILE_NAME));
    }

    private static Perl6File makeSettingSymbols(Project project, JSONArray settingJson) {
        if (project.isDisposed())
            return null;
        ExternalPerl6File perl6File = new ExternalPerl6File(project, new LightVirtualFile(SETTING_FILE_NAME));
        Perl6ExternalNamesParser parser = new Perl6ExternalNamesParser(project, perl6File, settingJson).parse();
        perl6File.setSymbols(parser.result());
        return perl6File;
    }

    public Perl6File getPsiFileForModule(Project project, String name, String invocation) {
        Map<String, Perl6File> cache = invocation.startsWith("use") ? useNameFileCache : needNameFileCache;
        Map<String, JSONArray> symbolCache = invocation.startsWith("use") ? useNameSymbolCache : needNameSymbolCache;
        // If we have anything in file cache, return it
        if (cache.containsKey(name))
            return cache.get(name);

        if (!myPackageStarted.contains(name)) {
            myPackageStarted.add(name);
            Thread thread = new Thread(() -> {
                try {
                    // if not, check if we have symbol cache, if yes, parse, save and return it
                    if (symbolCache.containsKey(name))
                        cache.compute(name, (n, v) -> constructExternalPsiFile(project, n, symbolCache.get(n)));
                    // if no symbol cache, compute as usual
                    cache.compute(name, (n, v) -> constructExternalPsiFile(project, n, invocation));
                    triggerCodeAnalysis(project);
                } catch (AssertionError e) {
                    // If the project was already disposed, do not die in a background thread
                }
            });
            thread.start();
        }
        return new ExternalPerl6File(project, new LightVirtualFile("DUMMY"));
    }

    private static Perl6File constructExternalPsiFile(Project project, String name, JSONArray externalsJSON) {
        LightVirtualFile dummy = new LightVirtualFile(name + ".pm6");
        ExternalPerl6File perl6File = new ExternalPerl6File(project, dummy);
        Perl6ExternalNamesParser parser = new Perl6ExternalNamesParser(project, perl6File, externalsJSON);
        perl6File.setSymbols(parser.result());
        return perl6File;
    }

    private static Perl6File constructExternalPsiFile(Project project, String name, String invocation) {
        LightVirtualFile dummy = new LightVirtualFile(name + ".pm6");
        ExternalPerl6File perl6File = new ExternalPerl6File(project, dummy);
        List<Perl6Symbol> symbols = loadModuleSymbols(project, perl6File, invocation);
        perl6File.setSymbols(symbols);
        return perl6File;
    }

    public void invalidateCaches() {
        moarBuildConfig = null;
        invalidateFileCache();
        invalidateSymbolCache();
    }

    public void invalidateFileCache() {
        myPackageStarted = ContainerUtil.newConcurrentSet();
        mySettingsStarted.set(false);
        useNameFileCache = new ConcurrentHashMap<>();
        needNameFileCache = new ConcurrentHashMap<>();
        setting = null;
    }

    public void invalidateSymbolCache() {
        useNameSymbolCache = new ConcurrentHashMap<>();
        needNameSymbolCache = new ConcurrentHashMap<>();
        settingJson = new JSONArray();
    }

    private static List<Perl6Symbol> loadModuleSymbols(Project project, Perl6File perl6File, String invocation) {

        if (invocation.equals("use nqp")) {
            return getNQPSymbols(project, perl6File);
        }

        String homePath = getSdkHomeByProject(project);
        File moduleSymbols = Perl6Utils.getResourceAsFile("symbols/perl6-module-symbols.p6");
        if (homePath == null) {
            LOG.warn(new ExecutionException("SDK path is not set"));
            return new ArrayList<>();
        } else if (moduleSymbols == null) {
            LOG.warn(new ExecutionException("Necessary distribution file is missing"));
            return new ArrayList<>();
        }
        try {
            Perl6CommandLine cmd = new Perl6CommandLine(project);
            cmd.setWorkDirectory(project.getBasePath());
            cmd.addParameters(moduleSymbols.getPath(), invocation);
            String text = String.join("\n", cmd.executeAndRead());
            return new Perl6ExternalNamesParser(project, perl6File, text).parse().result();
        } catch (ExecutionException e) {
            return new ArrayList<>();
        }
    }

    private static List<Perl6Symbol> getNQPSymbols(Project project, Perl6File perl6File) {
        List<String> ops = new ArrayList<>();
        File nqpSymbols = Perl6Utils.getResourceAsFile("symbols/nqp.ops");
        if (nqpSymbols == null) {
            ops.add("[]");
        } else {
            Path nqpSymbolsPath = nqpSymbols.toPath();
            try {
                ops = Files.readAllLines(nqpSymbolsPath);
            }
            catch (IOException e) {
                ops.add("[]");
            }
        }

        return new Perl6ExternalNamesParser(project, perl6File, String.join("\n", ops)).parse().result();
    }

    public static void contributeParentSymbolsFromCore(@NotNull Perl6SymbolCollector collector,
                                                       Perl6File coreSetting,
                                                       String parentName,
                                                       MOPSymbolsAllowed allowed) {
        Perl6SingleResolutionSymbolCollector parentCollector =
          new Perl6SingleResolutionSymbolCollector(parentName, Perl6SymbolKind.TypeOrConstant);
        coreSetting.contributeGlobals(parentCollector, new HashSet<>());
        if (parentCollector.isSatisfied()) {
            Perl6PackageDecl decl = (Perl6PackageDecl)parentCollector.getResult().getPsi();
            decl.contributeMOPSymbols(collector, allowed);
        }
    }
}
