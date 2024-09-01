package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuTry;

public class Perl6TrySurrounder extends Perl6ControlSurrounder<RakuTry> {
    public Perl6TrySurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuTry createElement(Project project) {
        return RakuElementFactory.createTryStatement(project);
    }

    @Override
    public String getTemplateDescription() {
        return "try";
    }

    @Override
    protected boolean isControl() {
        return false;
    }
}
