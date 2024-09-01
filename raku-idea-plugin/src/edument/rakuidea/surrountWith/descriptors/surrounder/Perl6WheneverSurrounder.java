package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuWheneverStatement;

public class Perl6WheneverSurrounder extends Perl6ControlSurrounder<RakuWheneverStatement> {
    public Perl6WheneverSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuWheneverStatement createElement(Project project) {
        return RakuElementFactory.createWheneverStatement(project);
    }

    @Override
    public String getTemplateDescription() {
        return "whenever";
    }
}
