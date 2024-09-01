package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuIntLiteral;
import edument.rakuidea.psi.effects.EffectCollection;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.sdk.RakuSdkType;
import edument.rakuidea.sdk.RakuSettingTypeId;
import org.jetbrains.annotations.NotNull;

public class RakuIntLiteralImpl extends ASTWrapperPsiElement implements RakuIntLiteral {
    public RakuIntLiteralImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull RakuType inferType() {
        return RakuSdkType.getInstance().getCoreSettingType(getProject(), RakuSettingTypeId.Int);
    }

    @Override
    @NotNull
    public EffectCollection inferEffects() {
        return EffectCollection.EMPTY;
    }
}
