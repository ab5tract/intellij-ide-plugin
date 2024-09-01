package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.*;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.psi.type.RakuUntyped;
import edument.rakuidea.sdk.RakuSdkType;
import edument.rakuidea.sdk.RakuSettingTypeId;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class RakuParenthesizedExprImpl extends ASTWrapperPsiElement implements RakuParenthesizedExpr {
    public RakuParenthesizedExprImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull RakuType inferType() {
        Collection<RakuStatement> children = PsiTreeUtil.findChildrenOfType(this, RakuStatement.class);
        ArrayList<RakuStatement> list = new ArrayList<>(children);
        if (list.size() == 0)
            return RakuSdkType.getInstance().getCoreSettingType(getProject(), RakuSettingTypeId.List);
        if (list.size() == 1 ||
            (list.size() == 2 && PsiTreeUtil.isAncestor(list.get(0), list.get(1), true))) {
            RakuPsiElement firstChild = (RakuPsiElement)list.get(0).getFirstChild();
            return firstChild.inferType();
        }
        return RakuUntyped.INSTANCE;
    }
}
