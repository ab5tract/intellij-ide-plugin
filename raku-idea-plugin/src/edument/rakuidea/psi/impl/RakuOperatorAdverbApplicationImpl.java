package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuOperatorAdverbApplication;
import org.jetbrains.annotations.NotNull;

public class RakuOperatorAdverbApplicationImpl extends ASTWrapperPsiElement implements RakuOperatorAdverbApplication {
    public RakuOperatorAdverbApplicationImpl(@NotNull ASTNode node) {
        super(node);
    }
}
