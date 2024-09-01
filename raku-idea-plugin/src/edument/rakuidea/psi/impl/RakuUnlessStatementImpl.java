package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuUnlessStatement;
import org.jetbrains.annotations.NotNull;

public class RakuUnlessStatementImpl extends ASTWrapperPsiElement implements RakuUnlessStatement {
    public RakuUnlessStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
