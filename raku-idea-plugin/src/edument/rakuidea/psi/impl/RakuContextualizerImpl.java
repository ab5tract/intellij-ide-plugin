package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuContextualizer;
import org.jetbrains.annotations.NotNull;

public class RakuContextualizerImpl extends ASTWrapperPsiElement implements RakuContextualizer {
    public RakuContextualizerImpl(@NotNull ASTNode node) {
        super(node);
    }
}
