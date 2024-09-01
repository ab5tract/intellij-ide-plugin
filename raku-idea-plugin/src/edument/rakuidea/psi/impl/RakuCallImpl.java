package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuCall;
import edument.rakuidea.psi.effects.Effect;
import edument.rakuidea.psi.effects.EffectCollection;
import org.jetbrains.annotations.NotNull;

public class RakuCallImpl extends ASTWrapperPsiElement implements RakuCall {
    public RakuCallImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull EffectCollection inferEffects() {
        return EffectCollection.of(Effect.IMPURE);
    }
}
