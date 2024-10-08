package org.raku.testing;

import com.intellij.execution.configurations.*;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.raku.run.RakuDebuggableConfiguration;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

abstract public class RakuTestRunConfiguration extends RunConfigurationBase<RunProfileState> implements RakuDebuggableConfiguration, LocatableConfiguration {
    // Kind and kind-specific fields
    private static final String TEST_KIND = "TEST_KIND";
    private RakuTestKind testKind;

    private static final String MODULE = "MODULE";
    private String moduleName;
    private static final String DIRECTORY_PATH = "DIRECTORY_PATH";
    private String directoryPath;
    private static final String FILE = "FILE";
    private String filePath;

    // Generic fields
    private static final String TEST_PATTERN = "TEST_PATTERN";
    private String testPattern = "";
    private static final String PARALELLISM_DEGREE = "PARALELLISM_DEGREE";
    private Integer parallelismDegree = 1;
    private static final String ENVS = "ENVS";
    private Map<String, String> myEnvs = new HashMap<>();
    private static final String PASS_PARENT_ENV = "PASS_PARENT_ENV";
    private boolean passParentEnvs = true;
    private static final String INTERPRETER_PARAMETERS = "INTERPRETER_PARAMETERS";
    private String interpreterArguments = "-Ilib";

    public RakuTestRunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory) {
        super(project, factory, "Raku test");
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new RakuTestSettingsEditor(getProject());
    }

    @Override
    public int getDebugPort() {
        return 9999;
    }

    @Override
    public void setDebugPort(int debugPort) {
    }

    @Override
    public boolean isStartSuspended() {
        return false;
    }

    @Override
    public void setStartSuspended(boolean startSuspended) {
    }

    @Override
    public void readExternal(@NotNull Element element) throws InvalidDataException {
        super.readExternal(element);

        // Read kind
        Element kind = element.getChild(TEST_KIND);
        testKind = kind == null ? RakuTestKind.ALL : (RakuTestKind.valueOf(kind.getText()));

        // Read specific options
        switch (testKind) {
            case ALL:
                break;
            case MODULE: {
                Element module = element.getChild(MODULE);
                moduleName = module == null ? "" : module.getText();
                break;
            }
            case DIRECTORY: {
                Element directory = element.getChild(DIRECTORY_PATH);
                directoryPath = directory == null ? "" : directory.getText();
                break;
            }
            case FILE: {
                Element file = element.getChild(FILE);
                filePath = file == null ? "" : file.getText();
                break;
            }
        }

        // Read generic options
        Element degree = element.getChild(PARALELLISM_DEGREE);
        parallelismDegree = degree == null ? 1 : Integer.valueOf(degree.getText());
        Element envs = element.getChild(ENVS);
        if (envs != null) {
            for (Element envVar : envs.getChildren()) {
                myEnvs.put(envVar.getName(), envVar.getValue());
            }
        }
        Element isPassParentEnv = element.getChild(PASS_PARENT_ENV);
        passParentEnvs = isPassParentEnv == null || Boolean.valueOf(isPassParentEnv.getText());
        Element params = element.getChild(INTERPRETER_PARAMETERS);
        interpreterArguments = params == null ? "-Ilib" : params.getText();
    }

    @Override
    public void writeExternal(@NotNull Element element) throws WriteExternalException {
        if (testKind == null) {
            testKind = RakuTestKind.ALL;
        }
        // Write kind
        element.addContent(new Element(TEST_KIND).setText(testKind.baseString()));
        // Write kind specific options
        switch (testKind) {
            case ALL:
                break;
            case MODULE: {
                element.addContent(new Element(MODULE).setText(moduleName));
                break;
            }
            case DIRECTORY: {
                element.addContent(new Element(DIRECTORY_PATH).setText(directoryPath));
                break;
            }
            case FILE: {
                element.addContent(new Element(FILE).setText(filePath));
                break;
            }
        }

        // Write generic options
        element.addContent(new Element(PARALELLISM_DEGREE).setText(String.valueOf(parallelismDegree)));
        Element envs = new Element(ENVS);
        for (Map.Entry<String, String> envVar : myEnvs.entrySet()) {
            Element e = new Element(envVar.getKey());
            e.setText(envVar.getValue());
            envs.addContent(e);
        }
        element.addContent(envs);
        element.addContent(new Element(PASS_PARENT_ENV).setText(String.valueOf(passParentEnvs)));
        element.addContent(new Element(INTERPRETER_PARAMETERS).setText(interpreterArguments));
    }

    public Integer getParallelismDegree() {
        return Objects.requireNonNullElse(parallelismDegree, 1);
    }

    public void setParallelismDegree(int parallelismDegree) {
        this.parallelismDegree = parallelismDegree;
    }

    protected Map<String, String> getEnvs() {
        return myEnvs;
    }

    protected void setEnvs(Map<String, String> envs) {
        myEnvs = envs;
    }

    protected boolean isPassParentEnvs() {
        return passParentEnvs;
    }

    protected void setPassParentEnvs(boolean passParentEnvs) {
        this.passParentEnvs = passParentEnvs;
    }

    protected RakuTestKind getTestKind() {
        return testKind == null ? RakuTestKind.ALL : testKind;
    }

    protected void setTestKind(RakuTestKind testKind) {
        this.testKind = testKind;
    }

    protected String getModuleName() {
        return moduleName;
    }

    protected void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    protected String getDirectoryPath() {
        return directoryPath;
    }

    protected void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    protected String getFilePath() {
        return filePath;
    }

    protected void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getInterpreterArguments() {
        return interpreterArguments;
    }

    protected void setInterpreterArguments(String interpreterArguments) {
        this.interpreterArguments = interpreterArguments;
    }

    protected String getTestPattern() {
        return testPattern;
    }

    protected void setTestPattern(String testPattern) {
        this.testPattern = testPattern;
    }

    @Override
    public @Nullable String suggestedName() {
        switch (getTestKind()) {
            case ALL: return "Test project";
            case MODULE: return "Test module " + getModuleName();
            case DIRECTORY: return "Test directory " + Paths.get(getDirectoryPath()).getFileName();
            case FILE: return "Test " + Paths.get(getFilePath()).getFileName();
        }
        return null;
    }

    @Override
    public boolean isGeneratedName() {
        return suggestedName() != null && Objects.equals(suggestedName(), getName());
    }
}
