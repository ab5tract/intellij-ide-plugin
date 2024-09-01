package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuBlock;
import edument.rakuidea.psi.RakuWhileStatement;
import org.jetbrains.annotations.NotNull;

public class RakuWhileStatementImpl extends ASTWrapperPsiElement implements RakuWhileStatement {
    public RakuWhileStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public RakuBlock getBlock() {
        return findChildByClass(RakuBlock.class);
    }
}
