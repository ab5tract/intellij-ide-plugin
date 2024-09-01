package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuBlockOrHash;
import edument.rakuidea.psi.RakuElementFactory;

public class Perl6HashSurrounder extends Perl6ControlSurrounder<RakuBlockOrHash> {
    public Perl6HashSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuBlockOrHash createElement(Project project) {
        return RakuElementFactory.createBlockOrHash(project);
    }

    @Override
    protected boolean isExpression() {
        return true;
    }

    @Override
    protected boolean isControl() {
        return false;
    }

    @Override
    public String getTemplateDescription() {
        return "{ }";
    }
}
