package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.TokenSet;
import edument.rakuidea.parsing.RakuTokenTypes;
import edument.rakuidea.psi.RakuInfix;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RakuInfixImpl extends ASTWrapperPsiElement implements RakuInfix {
    public RakuInfixImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getLeftSide() {
        return skipWhitespacesBackward();
    }

    @Nullable
    @Override
    public PsiElement getRightSide() {
        return skipWhitespacesForward();
    }

    @Override
    public PsiElement getOperator() {
        return findChildByType(TokenSet.create(RakuTokenTypes.INFIX));
    }

    @Override
    public PsiReference getReference() {
        return new RakuOpReference(this);
    }
}
