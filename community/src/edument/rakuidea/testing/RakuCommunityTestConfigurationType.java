package edument.rakuidea.testing;

import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import edument.rakuidea.RakuIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalIgnoreDependencyViolation
public class RakuCommunityTestConfigurationType extends ConfigurationTypeBase implements DumbAware {
    private static final String PERL6_TEST_CONFIGURATION_ID = "PERL6_TEST_CONFIGURATION";
    private final ConfigurationFactory myFactory = new Perl6ConfigurationFactory(this);

    protected RakuCommunityTestConfigurationType() {
        super(PERL6_TEST_CONFIGURATION_ID, "Raku test",
              "Run Raku tests", RakuIcons.CAMELIA);
        addFactory(new Perl6ConfigurationFactory(this));
    }

    ConfigurationFactory getFactory() {
        return myFactory;
    }

    @NotNull
    public static RakuCommunityTestConfigurationType getInstance() {
        return ConfigurationTypeUtil.findConfigurationType(RakuCommunityTestConfigurationType.class);
    }

    public static class Perl6CommunityTestRunConfiguration extends RakuTestRunConfiguration {
        Perl6CommunityTestRunConfiguration(@NotNull Project project, @NotNull ConfigurationFactory factory) {
            super(project, factory);
        }

        @Nullable
        @Override
        public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment environment) {
            return new RakuTestRunningState(environment);
        }
    }

    public static class Perl6ConfigurationFactory extends ConfigurationFactory {
        protected Perl6ConfigurationFactory(ConfigurationType configurationType) {
            super(configurationType);
        }

        @Override
        @NotNull
        public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
            return new Perl6CommunityTestRunConfiguration(project, this);
        }

        @Override
        public @NotNull String getId() {
            return "Raku test";
        }
    }
}
