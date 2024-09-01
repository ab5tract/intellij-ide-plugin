package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.PodBlockDelimited;
import org.jetbrains.annotations.NotNull;

public class PodBlockDelimitedImpl extends ASTWrapperPsiElement implements PodBlockDelimited {
    public PodBlockDelimitedImpl(@NotNull ASTNode node) {
        super(node);
    }
}
