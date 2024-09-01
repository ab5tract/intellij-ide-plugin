package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuQuasi;
import org.jetbrains.annotations.NotNull;

public class RakuQuasiImpl extends ASTWrapperPsiElement implements RakuQuasi {
    public RakuQuasiImpl(@NotNull ASTNode node) {
        super(node);
    }
}
