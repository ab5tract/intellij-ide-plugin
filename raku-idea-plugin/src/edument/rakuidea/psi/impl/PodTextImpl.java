package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.PodText;
import org.jetbrains.annotations.NotNull;

public class PodTextImpl extends ASTWrapperPsiElement implements PodText {
    public PodTextImpl(@NotNull ASTNode node) {
        super(node);
    }
}
