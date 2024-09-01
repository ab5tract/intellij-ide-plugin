package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuLazy;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.sdk.RakuSdkType;
import edument.rakuidea.sdk.RakuSettingTypeId;
import org.jetbrains.annotations.NotNull;

public class RakuLazyImpl extends ASTWrapperPsiElement implements RakuLazy {
    public RakuLazyImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull RakuType inferType() {
        return RakuSdkType.getInstance().getCoreSettingType(getProject(), RakuSettingTypeId.Seq);
    }
}
