package org.raku.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.process.KillableColoredProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.raku.cro.run.RakuCroRunConfigurationBase;
import org.raku.sdk.RakuSdkType;
import org.raku.utils.RakuCommandLine;
import org.raku.utils.RakuScriptRunner;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class RakuRunCommandLineState extends CommandLineState {
    protected List<String> command = new LinkedList<>();
    protected @NotNull RunProfile runConfiguration;
    protected boolean isDebug = false;

    public RakuRunCommandLineState(ExecutionEnvironment environment) {
        super(environment);
        runConfiguration = getEnvironment().getRunProfile();
    }

    protected void populateRunCommand() throws ExecutionException {
        setInterpreterParameters();
    }

    protected void setInterpreterParameters() {
        String params = ((RakuRunConfiguration)runConfiguration).getInterpreterParameters();
        if (params != null && !params.trim().isEmpty())
            command.addAll(Arrays.asList(params.split(" ")));
    }

    @NotNull
    @Override
    protected ProcessHandler startProcess() throws ExecutionException {
        // At the beginning of starting a process, we always
        // check project's SDK for being valid, so that
        // all run configurations can assume they are running with a proper SDK
        checkSdk();
        populateRunCommand();
        setScript();
        GeneralCommandLine cmd;
        if (isDebug) {
            if (System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win"))
                cmd = new RakuCommandLine(getEnvironment().getProject(), ((RakuRunConfiguration)runConfiguration).getDebugPort());
            else
                cmd = new RakuScriptRunner(getEnvironment().getProject(), ((RakuRunConfiguration)runConfiguration).getDebugPort());
        } else {
            if (System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win"))
                cmd = new RakuCommandLine(getEnvironment().getProject());
            else
                cmd = new RakuScriptRunner(getEnvironment().getProject());
        }
        cmd.setWorkDirectory(((RakuRunConfiguration)runConfiguration).getWorkingDirectory());
        cmd.addParameters(command);
        setEnvironment(cmd);
        KillableColoredProcessHandler handler = new KillableColoredProcessHandler(cmd);
        ProcessTerminatedListener.attach(handler, getEnvironment().getProject());
        setListeners(handler);
        return handler;
    }

    /**
     * Checks SDK of project to be valid and is executed before process is started.
     * Overload it with caution and in custom `startProcess` implementations
     * always call either parent `startProcess` or `checkSdk` method.
     */
    protected void checkSdk() throws ExecutionException {
        String path = RakuSdkType.getSdkHomeByProject(getEnvironment().getProject());
        if (path == null)
            throw new ExecutionException("Raku SDK is not set for the project, please set one");
    }

    protected void setEnvironment(GeneralCommandLine cmd) {
        cmd.withEnvironment(((RakuRunConfiguration)runConfiguration).getEnvs());
        if (runConfiguration instanceof RakuCroRunConfigurationBase &&
            ((RakuCroRunConfigurationBase)runConfiguration).getCroDevMode()) {
            cmd.withEnvironment("CRO_DEV", "1");
        }
    }

    protected void setListeners(KillableColoredProcessHandler handler) {}

    private void setScript() {
        command.add(((RakuRunConfiguration)runConfiguration).getScriptPath());
        String params = ((RakuRunConfiguration)runConfiguration).getProgramParameters();
        // To avoid a call like `rakuidea script.p6 ""`
        if (params != null && !params.trim().isEmpty())
            command.addAll(Arrays.asList(params.split(" ")));
    }
}
