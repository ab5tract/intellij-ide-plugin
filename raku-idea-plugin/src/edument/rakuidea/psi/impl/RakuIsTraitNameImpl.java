package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.util.IncorrectOperationException;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuIsTraitName;
import edument.rakuidea.psi.RakuIsTraitReference;
import edument.rakuidea.psi.RakuLongName;
import org.jetbrains.annotations.NotNull;

public class RakuIsTraitNameImpl extends ASTWrapperPsiElement implements RakuIsTraitName {
    public RakuIsTraitNameImpl(ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return new RakuIsTraitReference(this);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        RakuLongName type = RakuElementFactory.createIsTraitName(getProject(), name);
        RakuLongName longName = findChildByClass(RakuLongName.class);
        if (longName != null) {
            ASTNode keyNode = longName.getNode();
            ASTNode newKeyNode = type.getNode();
            getNode().replaceChild(keyNode, newKeyNode);
        }
        return this;
    }
}
