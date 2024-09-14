package org.raku.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.raku.psi.RakuZipMetaOp;
import org.jetbrains.annotations.NotNull;

public class RakuZipMetaOpImpl extends ASTWrapperPsiElement implements RakuZipMetaOp {
    public RakuZipMetaOpImpl(@NotNull ASTNode node) {
        super(node);
    }
}
