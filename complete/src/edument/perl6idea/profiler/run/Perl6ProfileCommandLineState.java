package edument.perl6idea.profiler.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import edument.perl6idea.run.Perl6RunCommandLineState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;

public class Perl6ProfileCommandLineState extends Perl6RunCommandLineState {
    static Logger LOG = Logger.getInstance(Perl6ProfileCommandLineState.class);
    private VirtualFile resultsFile;
    private File tempFile = null;

    public Perl6ProfileCommandLineState(ExecutionEnvironment environment) {
        super(environment);
    }

    public Perl6ProfileCommandLineState(ExecutionEnvironment environment, VirtualFile resultsFile) {
        super(environment);
        this.resultsFile = resultsFile;
        this.tempFile = Paths.get(resultsFile.getPath()).toFile();
    }

    @Override
    protected @NotNull ProcessHandler startProcess() throws ExecutionException {
        if (resultsFile == null)
            return super.startProcess();
        else
            return new ProcessHandler() {
                @Override
                protected void destroyProcessImpl() {}

                @Override
                protected void detachProcessImpl() {}

                @Override
                public boolean detachIsDefault() {
                    return false;
                }

                @Override
                public @Nullable OutputStream getProcessInput() {
                    return null;
                }
            };
    }

    @Override
    protected void populateRunCommand() throws ExecutionException {
        if (resultsFile != null) {
            return;
        }

        String canonicalPath;
        try {
            tempFile = FileUtil.createTempFile("comma-profiler", ".sql");
            // We use safe canonical path here, as running profiler is not performance-critical operation
            canonicalPath = tempFile.getCanonicalPath();
        }
        catch (IOException e) {
            LOG.warn(e);
            throw new ExecutionException(e.getMessage());
        }
        command.add(String.format("--profile=%s", canonicalPath));
        setInterpreterParameters();
    }

    @Nullable
    public File getProfileResultsFile() {
        return tempFile;
    }
}
