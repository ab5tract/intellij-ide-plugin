package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuImportStatement;
import org.jetbrains.annotations.NotNull;

public class RakuImportStatementImpl extends ASTWrapperPsiElement implements RakuImportStatement {
    public RakuImportStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
