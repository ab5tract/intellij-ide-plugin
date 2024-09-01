package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuPointyBlock;

public class Perl6PointyBlockSurrounder extends Perl6ControlSurrounder<RakuPointyBlock> {
    public Perl6PointyBlockSurrounder(boolean isSurrounder) {
        super(isSurrounder);
    }

    @Override
    protected RakuPointyBlock createElement(Project project) {
        return RakuElementFactory.createPointyBlock(project);
    }

    @Override
    public String getTemplateDescription() {
        return "-> {}";
    }

    @Override
    protected boolean isControl() {
        return false;
    }
}
