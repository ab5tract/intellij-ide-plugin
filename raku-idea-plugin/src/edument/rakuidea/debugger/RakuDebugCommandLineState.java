package edument.rakuidea.debugger;

import com.intellij.execution.runners.ExecutionEnvironment;
import edument.rakuidea.run.RakuRunCommandLineState;

public class RakuDebugCommandLineState extends RakuRunCommandLineState {
    public RakuDebugCommandLineState(ExecutionEnvironment environment) {
        super(environment);
        isDebug = true;
    }
}
