package org.raku.module.builder;

import com.intellij.ide.util.projectWizard.ModuleNameLocationSettings;
import com.intellij.ide.util.projectWizard.SettingsStep;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.raku.filetypes.RakuModuleFileType;
import org.raku.filetypes.RakuTestFileType;
import org.raku.language.RakuLanguageVersion;
import org.raku.metadata.RakuMetaDataComponent;
import org.raku.module.RakuModuleWizardStep;
import org.raku.utils.RakuUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class RakuModuleBuilderModule implements RakuModuleBuilderGeneric {
    private static final Logger LOG = Logger.getInstance(RakuModuleBuilderModule.class);
    private String myModuleName;

    @Override
    public void setupRootModelOfPath(@NotNull ModifiableRootModel model,
                                     Path path,
                                     RakuLanguageVersion languageVersion) {
        RakuMetaDataComponent metaData = model.getModule().getService(RakuMetaDataComponent.class);
        VirtualFile sourceRoot = LocalFileSystem.getInstance().refreshAndFindFileByIoFile(path.toFile());
        Path directoryName = path.getFileName();
        if (Objects.equals(directoryName.toString(), "lib")) {
            stubModule(metaData, path, myModuleName, true,
                       false, sourceRoot == null ? null : sourceRoot.getParent(), "Empty",
                       false, languageVersion);
        } else if (Objects.equals(directoryName.toString(), "t")) {
            stubTest(path, "00-sanity.t",
                     Collections.singletonList(myModuleName), languageVersion);
        }
    }

    @Override
    public void loadFromDialogData(Map<String, String> data) {
        myModuleName = data.get(RakuModuleWizardStep.MODULE_NAME);
    }

    @Override
    public String[] getSourceDirectories() {
        return new String[]{"lib", "t"};
    }

    public static String stubModule(RakuMetaDataComponent metaData,
                                    Path moduleLibraryPath,
                                    String moduleName,
                                    boolean firstModule,
                                    boolean shouldOpenEditor,
                                    VirtualFile root,
                                    String moduleType,
                                    boolean isUnitScoped,
                                    RakuLanguageVersion languageVersion) {
        if (firstModule) {
            try {
                metaData.createStubMetaFile(moduleName, root, shouldOpenEditor);
            }
            catch (IOException e) {
                LOG.warn(e);
            }
        }
        if (moduleLibraryPath.endsWith("lib")) {
            ApplicationManager.getApplication().invokeLater(() -> metaData.addNamespaceToProvides(moduleName, RakuModuleFileType.INSTANCE.getDefaultExtension()));
        }
        String modulePath = Paths.get(moduleLibraryPath.toString(), moduleName.split("::")) + "." + RakuModuleFileType.INSTANCE.getDefaultExtension();
        new File(modulePath).getParentFile().mkdirs();
        List<String> code = new ArrayList<>(getModuleCodeByType(moduleType, moduleName, isUnitScoped));
        if (languageVersion != null)
            code.add(0, String.format("use v%s;", languageVersion));
        RakuUtils.writeCodeToPath(Paths.get(modulePath), code);
        if (moduleType.equals("Monitor")) {
            metaData.addDepends("OO::Monitors");
        }
        if (moduleType.equals("Model")) {
            metaData.addDepends("Red");
        }
        return modulePath;
    }

    private static List<String> getModuleCodeByType(String type,
                                                    String name,
                                                    boolean isUnitScoped) {
        if (isUnitScoped) {
            String declText = String.format("unit %s %s;", type.toLowerCase(Locale.ENGLISH), name);
            switch (type) {
                case "Class":
                case "Role":
                case "Grammar":
                case "Module":
                    return Arrays.asList(declText, "");
                case "Monitor":
                    return Arrays.asList(
                      "use OO::Monitors;", "",
                      declText, "");
                case "Model":
                    return Arrays.asList(
                      "use Red;", "",
                      declText, "");
                default:
                    return Collections.singletonList("");
            }
        }
        else {
            String declText = String.format("%s %s {", type.toLowerCase(Locale.ENGLISH), name);
            switch (type) {
                case "Class":
                case "Role":
                case "Grammar":
                case "Module":
                    return Arrays.asList(declText, "", "}");
                case "Monitor":
                    return Arrays.asList(
                      "use OO::Monitors;", "",
                      declText, "", "}");
                case "Model":
                    return Arrays.asList(
                      "use Red;", "",
                      declText, "", "}");
                default:
                    return Collections.singletonList("");
            }
        }
    }

    public static String stubTest(Path testDirectoryPath,
                                  String fileName,
                                  List<String> imports,
                                  RakuLanguageVersion languageVersion) {
        Path testPath = testDirectoryPath.resolve(fileName);
        // If no extension, add default `.t`
        if (!fileName.contains("."))
            testPath = Paths.get(testDirectoryPath.toString(), fileName + "." + RakuTestFileType.INSTANCE.getDefaultExtension());
        List<String> lines = new LinkedList<>();
        lines.add(String.format("use v%s;", languageVersion));
        imports.forEach(i -> lines.add(String.format("use %s;", i)));
        lines.addAll(Arrays.asList("use Test;", "", "done-testing;"));
        RakuUtils.writeCodeToPath(testPath, lines);
        return testPath.toString();
    }

    @Override
    public void modifySettingsStep(SettingsStep step) {
        final ModuleNameLocationSettings nameField = step.getModuleNameLocationSettings();
        if (myModuleName != null && nameField != null)
            nameField.setModuleName(StringUtil.sanitizeJavaIdentifier(myModuleName));
    }
}
