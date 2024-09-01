package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuUnlessStatement;

public class Perl6UnlessSurrounder extends Perl6ConditionalSurrounder<RakuUnlessStatement> {
    public Perl6UnlessSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuUnlessStatement createElement(Project project) {
        return RakuElementFactory.createUnlessStatement(project);
    }

    @Override
    public String getTemplateDescription() {
        return "unless";
    }
}
