package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuGivenStatement;

public class Perl6GivenSurrounder extends Perl6ControlSurrounder<RakuGivenStatement> {
    public Perl6GivenSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuGivenStatement createElement(Project project) {
        return RakuElementFactory.createGivenStatement(project);
    }

    @Override
    public String getTemplateDescription() {
        return "given";
    }
}
