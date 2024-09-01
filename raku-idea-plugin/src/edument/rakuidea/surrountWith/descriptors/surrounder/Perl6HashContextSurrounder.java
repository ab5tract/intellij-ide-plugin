package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuContextualizer;
import edument.rakuidea.psi.RakuElementFactory;

public class Perl6HashContextSurrounder extends Perl6ContextualizerSurrounder<RakuContextualizer> {
    public Perl6HashContextSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected RakuContextualizer createElement(Project project) {
        return RakuElementFactory.createContextualizer(project, RakuElementFactory.HASH_CONTEXTUALIZER);
    }

    @Override
    public String getTemplateDescription() {
        return "%(  )";
    }
}
