package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuOperatorAdverb;
import org.jetbrains.annotations.NotNull;

public class RakuOperatorAdverbImpl extends ASTWrapperPsiElement implements RakuOperatorAdverb {
    public RakuOperatorAdverbImpl(@NotNull ASTNode node) {
        super(node);
    }
}
