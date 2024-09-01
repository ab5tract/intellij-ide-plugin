package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.PodBlockParagraph;
import org.jetbrains.annotations.NotNull;

public class PodBlockParagraphImpl extends ASTWrapperPsiElement implements PodBlockParagraph {
    public PodBlockParagraphImpl(@NotNull ASTNode node) {
        super(node);
    }

}
