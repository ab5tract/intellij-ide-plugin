package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuRegexAtom;
import edument.rakuidea.psi.RakuRegexGroup;
import org.jetbrains.annotations.NotNull;

public class RakuRegexGroupImpl extends ASTWrapperPsiElement implements RakuRegexGroup {
    public RakuRegexGroupImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public boolean mightMatchZeroWidth() {
        return atomsMightMatchZeroWidth(PsiTreeUtil.getChildrenOfType(this, RakuRegexAtom.class));
    }
}
