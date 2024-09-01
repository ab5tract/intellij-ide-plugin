package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuWhenStatement;
import org.jetbrains.annotations.NotNull;

public class RakuWhenStatementImpl extends ASTWrapperPsiElement implements RakuWhenStatement {
    public RakuWhenStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
