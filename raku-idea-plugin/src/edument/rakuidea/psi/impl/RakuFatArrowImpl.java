package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import edument.rakuidea.psi.RakuFatArrow;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.sdk.RakuSdkType;
import edument.rakuidea.sdk.RakuSettingTypeId;
import org.jetbrains.annotations.NotNull;

public class RakuFatArrowImpl extends ASTWrapperPsiElement implements RakuFatArrow {
    public RakuFatArrowImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull RakuType inferType() {
        return RakuSdkType.getInstance().getCoreSettingType(getProject(), RakuSettingTypeId.Pair);
    }

    @Override
    public String getKey() {
        return getFirstChild().getText();
    }

    @Override
    public PsiElement getValue() {
        return getLastChild();
    }
}
