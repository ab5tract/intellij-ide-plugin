package edument.rakuidea.coverage;

import com.intellij.execution.configurations.RunProfile;
import edument.rakuidea.debugger.RakuDefaultRunner;
import edument.rakuidea.run.RakuCompleteRunConfigurationType;
import edument.rakuidea.run.RakuRunConfiguration;
import edument.rakuidea.testing.RakuCompleteTestConfigurationType;
import edument.rakuidea.testing.RakuTestRunConfiguration;
import org.jetbrains.annotations.NotNull;

public class RakuCoverageRunner extends RakuDefaultRunner {
    @NotNull
    @Override
    public String getRunnerId() {
        return "Raku Coverage";
    }

    @Override
    public boolean canRun(@NotNull String executorId, @NotNull RunProfile profile) {
        return "Coverage".equals(executorId) &&
               (profile instanceof RakuRunConfiguration || profile instanceof RakuTestRunConfiguration ||
                profile instanceof RakuCompleteRunConfigurationType || profile instanceof RakuCompleteTestConfigurationType);
    }
}
