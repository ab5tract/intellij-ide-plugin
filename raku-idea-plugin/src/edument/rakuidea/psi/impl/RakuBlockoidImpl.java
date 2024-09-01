package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuBlockoid;
import org.jetbrains.annotations.NotNull;

public class RakuBlockoidImpl extends ASTWrapperPsiElement implements RakuBlockoid {
    public RakuBlockoidImpl(@NotNull ASTNode node) {
        super(node);
    }
}
