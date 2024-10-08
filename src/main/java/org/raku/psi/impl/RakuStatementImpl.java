package org.raku.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.raku.psi.RakuPsiElement;
import org.raku.psi.RakuStatement;
import org.raku.psi.effects.EffectCollection;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class RakuStatementImpl extends ASTWrapperPsiElement implements RakuStatement {
    public RakuStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull EffectCollection inferEffects() {
        return Arrays.stream(getChildren())
            .filter(c -> c instanceof RakuPsiElement)
            .map(c -> ((RakuPsiElement)c).inferEffects())
            .reduce(EffectCollection.EMPTY, EffectCollection::merge);
    }
}
