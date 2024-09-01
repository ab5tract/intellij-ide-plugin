package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuStatementModCond;
import org.jetbrains.annotations.NotNull;

public class RakuStatementModCondImpl extends ASTWrapperPsiElement implements RakuStatementModCond {
    public RakuStatementModCondImpl(@NotNull ASTNode node) {
        super(node);
    }
}
