package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuValueConstraint;
import org.jetbrains.annotations.NotNull;

public class RakuValueConstraintImpl extends ASTWrapperPsiElement implements RakuValueConstraint {
    public RakuValueConstraintImpl(@NotNull ASTNode node) {
        super(node);
    }
}
