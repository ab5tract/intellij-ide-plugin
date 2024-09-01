package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuReact;
import org.jetbrains.annotations.NotNull;

public class RakuReactImpl extends ASTWrapperPsiElement implements RakuReact {
    public RakuReactImpl(@NotNull ASTNode node) {
        super(node);
    }
}
