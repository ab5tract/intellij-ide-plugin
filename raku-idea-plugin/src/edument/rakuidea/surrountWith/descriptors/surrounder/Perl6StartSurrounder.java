package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuStart;

public class Perl6StartSurrounder extends Perl6ControlSurrounder<RakuStart> {
    public Perl6StartSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuStart createElement(Project project) {
        return RakuElementFactory.createStartStatement(project);
    }

    @Override
    public String getTemplateDescription() {
        return "start";
    }

    @Override
    protected boolean isControl() {
        return false;
    }
}
