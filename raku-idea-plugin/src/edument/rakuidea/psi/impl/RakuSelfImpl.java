package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuPackageDecl;
import edument.rakuidea.psi.RakuSelf;
import edument.rakuidea.psi.RakuSelfReference;
import edument.rakuidea.psi.type.RakuSelfType;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.psi.type.RakuUntyped;
import org.jetbrains.annotations.NotNull;

public class RakuSelfImpl extends ASTWrapperPsiElement implements RakuSelf {
    public RakuSelfImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return new RakuSelfReference(this);
    }

    @Override
    public @NotNull RakuType inferType() {
        RakuPackageDecl packageDecl = PsiTreeUtil.getParentOfType(this, RakuPackageDecl.class);
        return packageDecl != null
                ? new RakuSelfType(packageDecl)
                : RakuUntyped.INSTANCE;
    }
}
