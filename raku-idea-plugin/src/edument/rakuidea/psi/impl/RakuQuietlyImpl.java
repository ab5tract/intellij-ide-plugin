package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuQuietly;
import org.jetbrains.annotations.NotNull;

public class RakuQuietlyImpl extends ASTWrapperPsiElement implements RakuQuietly {
    public RakuQuietlyImpl(@NotNull ASTNode node) {
        super(node);
    }
}
