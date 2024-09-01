package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuForStatement;

public class Perl6ForSurrounder extends Perl6ControlSurrounder<RakuForStatement> {
    public Perl6ForSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuForStatement createElement(Project project) {
        return RakuElementFactory.createForStatement(project);
    }

    @Override
    public String getTemplateDescription() {
        return "for";
    }
}
