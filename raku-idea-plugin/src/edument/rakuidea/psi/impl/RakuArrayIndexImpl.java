package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuArrayIndex;
import org.jetbrains.annotations.NotNull;

public class RakuArrayIndexImpl extends ASTWrapperPsiElement implements RakuArrayIndex {
    public RakuArrayIndexImpl(@NotNull ASTNode node) {
        super(node);
    }
}
