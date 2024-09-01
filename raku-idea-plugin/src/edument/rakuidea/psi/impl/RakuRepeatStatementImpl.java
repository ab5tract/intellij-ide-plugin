package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuRepeatStatement;
import org.jetbrains.annotations.NotNull;

public class RakuRepeatStatementImpl extends ASTWrapperPsiElement implements RakuRepeatStatement {
    public RakuRepeatStatementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
