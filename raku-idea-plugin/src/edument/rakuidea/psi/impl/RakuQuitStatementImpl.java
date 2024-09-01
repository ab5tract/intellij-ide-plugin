package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuQuitStatement;
import org.jetbrains.annotations.NotNull;

public class RakuQuitStatementImpl extends ASTWrapperPsiElement implements RakuQuitStatement {
    public RakuQuitStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
