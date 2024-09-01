package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuContextualizer;
import edument.rakuidea.psi.RakuElementFactory;

public class Perl6ArrayContextSurrounder extends Perl6ContextualizerSurrounder<RakuContextualizer> {
    public Perl6ArrayContextSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuContextualizer createElement(Project project) {
        return RakuElementFactory.createContextualizer(project, RakuElementFactory.ARRAY_CONTEXTUALIZER);
    }

    @Override
    public String getTemplateDescription() {
        return "@(  )";
    }
}
