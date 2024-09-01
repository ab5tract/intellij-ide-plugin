package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuUntilStatement;
import org.jetbrains.annotations.NotNull;

public class RakuUntilStatementImpl extends ASTWrapperPsiElement implements RakuUntilStatement {
    public RakuUntilStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
