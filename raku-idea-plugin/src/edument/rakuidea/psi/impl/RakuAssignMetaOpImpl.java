package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuAssignMetaOp;
import org.jetbrains.annotations.NotNull;

public class RakuAssignMetaOpImpl extends ASTWrapperPsiElement implements RakuAssignMetaOp {
    public RakuAssignMetaOpImpl(@NotNull ASTNode node) {
        super(node);
    }
}
