package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuOnce;
import org.jetbrains.annotations.NotNull;

public class RakuOnceImpl extends ASTWrapperPsiElement implements RakuOnce {
    public RakuOnceImpl(@NotNull ASTNode node) {
        super(node);
    }
}
