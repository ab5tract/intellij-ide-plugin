package edument.rakuidea.cro.run;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfileState;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.openapi.project.Project;
import edument.rakuidea.RakuIcons;
import edument.rakuidea.debugger.RakuDebugCommandLineState;
import edument.rakuidea.run.RakuRunCommandLineState;
import edument.rakuidea.timeline.RakuTimelineCommandLineState;
import edument.rakuidea.timeline.RakuTimelineExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalIgnoreDependencyViolation
public class RakuCommunityCroRunConfigurationType extends ConfigurationTypeBase {
    protected static final String PERL6_CRO_RUN_CONFIGURATION_ID = "PERL6_CRO_RUN_CONFIGURATION";

    protected RakuCommunityCroRunConfigurationType() {
        super(PERL6_CRO_RUN_CONFIGURATION_ID, "Cro Service", "Run Cro service", RakuIcons.CRO);
        addFactory(new ConfigurationFactory(this) {
            @Override
            public @NotNull String getId() {
                return "Cro Service";
            }

            @NotNull
            @Override
            public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
                return new RakuCroRunConfiguration(project, this, "Run Cro service");
            }
        });
    }

    @NotNull
    public static RakuCommunityCroRunConfigurationType getInstance() {
        return CONFIGURATION_TYPE_EP.findExtension(RakuCommunityCroRunConfigurationType.class);
    }

    private static class RakuCroRunConfiguration extends RakuCroRunConfigurationBase {
        RakuCroRunConfiguration(Project project, ConfigurationFactory factory, String name) {
            super(project, factory, name);
            configureCro(project);
        }

        @Nullable
        @Override
        public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) throws ExecutionException {
            if (executor instanceof DefaultDebugExecutor) {
                return new RakuDebugCommandLineState(environment);
            }
            else if (executor instanceof RakuTimelineExecutor) {
                return new RakuTimelineCommandLineState(environment);
            }
            return new RakuRunCommandLineState(environment);
        }
    }
}
