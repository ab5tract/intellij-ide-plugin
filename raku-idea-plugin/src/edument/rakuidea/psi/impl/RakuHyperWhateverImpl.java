package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuHyperWhatever;
import org.jetbrains.annotations.NotNull;

public class RakuHyperWhateverImpl extends ASTWrapperPsiElement implements RakuHyperWhatever {
    public RakuHyperWhateverImpl(@NotNull ASTNode node) {
        super(node);
    }
}
