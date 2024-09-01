package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuStatementList;
import org.jetbrains.annotations.NotNull;

public class RakuStatementListImpl extends ASTWrapperPsiElement implements RakuStatementList {
    public RakuStatementListImpl(@NotNull ASTNode node) {
        super(node);
    }
}
