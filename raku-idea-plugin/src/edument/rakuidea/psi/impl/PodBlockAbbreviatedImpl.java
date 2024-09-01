package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.PodBlockAbbreviated;
import org.jetbrains.annotations.NotNull;

public class PodBlockAbbreviatedImpl extends ASTWrapperPsiElement implements PodBlockAbbreviated {
    public PodBlockAbbreviatedImpl(@NotNull ASTNode node) {
        super(node);
    }
}
