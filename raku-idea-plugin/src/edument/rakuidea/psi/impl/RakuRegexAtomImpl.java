package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuRegexAtom;
import edument.rakuidea.psi.RakuRegexQuantifier;
import org.jetbrains.annotations.NotNull;

public class RakuRegexAtomImpl extends ASTWrapperPsiElement implements RakuRegexAtom {
    public RakuRegexAtomImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public boolean mightMatchZeroWidth() {
        // See if it is quantified with ? or *, which can trivially match zero
        // width.
        RakuRegexQuantifier quantifier = PsiTreeUtil.getChildOfType(this, RakuRegexQuantifier.class);
        if (quantifier != null) {
            String quantText = quantifier.getFirstChild().getText();
            if (quantText.equals("?") || quantText.equals("*"))
                return true;
        }

        // Otherwise, not sure.
        return false;
    }
}
