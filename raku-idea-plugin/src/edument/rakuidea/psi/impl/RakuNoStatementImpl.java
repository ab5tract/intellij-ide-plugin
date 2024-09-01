package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuNoStatement;
import org.jetbrains.annotations.NotNull;

public class RakuNoStatementImpl extends ASTWrapperPsiElement implements RakuNoStatement {
    public RakuNoStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
