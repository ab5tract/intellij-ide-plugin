package edument.rakuidea.run;

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
import edument.rakuidea.timeline.RakuTimelineCommandLineState;
import edument.rakuidea.timeline.RakuTimelineExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalIgnoreDependencyViolation
public class RakuCommunityRunConfigurationType extends ConfigurationTypeBase {
    protected static final String PERL6_RUN_CONFIGURATION_ID = "PERL6_RUN_CONFIGURATION";

    protected RakuCommunityRunConfigurationType() {
        super(PERL6_RUN_CONFIGURATION_ID, "Raku",
              "Raku run configuration", RakuIcons.CAMELIA);
        addFactory(new ConfigurationFactory(this) {
            @Override
            public @NotNull String getId() {
                return "Raku";
            }

            @NotNull
            @Override
            public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
                return new RakuCommunityRunConfiguration(project, this, "Run script");
            }
        });
    }

    @NotNull
    public static RakuCommunityRunConfigurationType getInstance() {
        return CONFIGURATION_TYPE_EP.findExtension(RakuCommunityRunConfigurationType.class);
    }

    private static class RakuCommunityRunConfiguration extends RakuRunConfiguration {
        public RakuCommunityRunConfiguration(Project project,
                                             ConfigurationFactory factory,
                                             String script) {
            super(project, factory, script);
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
