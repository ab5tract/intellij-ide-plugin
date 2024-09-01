package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuHyperMetaOp;
import org.jetbrains.annotations.NotNull;

public class RakuHyperMetaOpImpl extends ASTWrapperPsiElement implements RakuHyperMetaOp {
    public RakuHyperMetaOpImpl(@NotNull ASTNode node) {
        super(node);
    }
}
