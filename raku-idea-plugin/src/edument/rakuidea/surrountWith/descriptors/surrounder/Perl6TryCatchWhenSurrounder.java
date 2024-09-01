package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuTry;
import edument.rakuidea.psi.RakuWhenStatement;

public class Perl6TryCatchWhenSurrounder extends Perl6GenericTrySurrounder<RakuTry> {
    public Perl6TryCatchWhenSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected String createBranch() {
        return "when True {}";
    }

    @Override
    protected PsiElement getAnchor(RakuTry surrounder) {
        RakuWhenStatement whenStatement = PsiTreeUtil.findChildOfType(surrounder.getBlock(), RakuWhenStatement.class);
        return whenStatement == null ? null : whenStatement.getTopic();
    }

    @Override
    public String getTemplateDescription() {
        return "try { CATCH { when } }";
    }
}
