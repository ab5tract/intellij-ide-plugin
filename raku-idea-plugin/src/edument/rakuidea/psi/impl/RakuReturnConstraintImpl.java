package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import edument.rakuidea.psi.RakuReturnConstraint;
import edument.rakuidea.psi.RakuTypeName;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.psi.type.RakuUntyped;
import org.jetbrains.annotations.NotNull;

import static edument.rakuidea.parsing.RakuElementTypes.TYPE_NAME;

public class RakuReturnConstraintImpl extends ASTWrapperPsiElement implements RakuReturnConstraint {
    public RakuReturnConstraintImpl(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public RakuType getReturnType() {
        PsiElement typeName = findChildByType(TYPE_NAME);
        return typeName instanceof RakuTypeName
               ? ((RakuTypeName)typeName).inferType()
               : RakuUntyped.INSTANCE;
    }
}
