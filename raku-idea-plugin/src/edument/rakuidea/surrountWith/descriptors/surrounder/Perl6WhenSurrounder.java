package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuWhenStatement;

public class Perl6WhenSurrounder extends Perl6ControlSurrounder<RakuWhenStatement> {
    public Perl6WhenSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuWhenStatement createElement(Project project) {
        return RakuElementFactory.createWhenStatement(project);
    }

    @Override
    public String getTemplateDescription() {
        return "when";
    }
}
