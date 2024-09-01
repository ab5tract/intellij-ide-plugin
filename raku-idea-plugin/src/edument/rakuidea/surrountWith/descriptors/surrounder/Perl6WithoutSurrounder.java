package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuWithoutStatement;

public class Perl6WithoutSurrounder extends Perl6ConditionalSurrounder<RakuWithoutStatement> {
    public Perl6WithoutSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuWithoutStatement createElement(Project project) {
        return RakuElementFactory.createWithoutStatement(project);
    }

    @Override
    public String getTemplateDescription() {
        return "without";
    }

}
