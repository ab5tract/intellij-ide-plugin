package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuTerm;
import org.jetbrains.annotations.NotNull;

public class RakuTermImpl extends ASTWrapperPsiElement implements RakuTerm {
    public RakuTermImpl(@NotNull ASTNode node) {
        super(node);
    }
}
