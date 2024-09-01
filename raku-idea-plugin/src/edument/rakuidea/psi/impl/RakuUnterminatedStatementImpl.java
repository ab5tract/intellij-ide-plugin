package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuUnterminatedStatement;
import org.jetbrains.annotations.NotNull;

public class RakuUnterminatedStatementImpl extends ASTWrapperPsiElement implements RakuUnterminatedStatement {
    public RakuUnterminatedStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
