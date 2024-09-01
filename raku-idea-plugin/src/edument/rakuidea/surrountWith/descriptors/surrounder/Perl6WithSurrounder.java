package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuIfStatement;

public class Perl6WithSurrounder extends Perl6ConditionalSurrounder<RakuIfStatement> {
    public Perl6WithSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuIfStatement createElement(Project project) {
        return RakuElementFactory.createIfStatement(project, false, 1);
    }

    @Override
    public String getTemplateDescription() {
        return "with";
    }
}
