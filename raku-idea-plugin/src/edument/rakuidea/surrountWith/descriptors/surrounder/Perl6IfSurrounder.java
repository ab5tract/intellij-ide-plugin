package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuIfStatement;

public class Perl6IfSurrounder extends Perl6ConditionalSurrounder<RakuIfStatement> {
    public Perl6IfSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuIfStatement createElement(Project project) {
        return RakuElementFactory.createIfStatement(project, true, 1);
    }

    @Override
    public String getTemplateDescription() {
        return "if";
    }
}
