package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuLoopStatement;
import org.jetbrains.annotations.NotNull;

public class RakuLoopStatementImpl extends ASTWrapperPsiElement implements RakuLoopStatement {
    public RakuLoopStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
