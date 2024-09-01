package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuRegexSigspace;
import org.jetbrains.annotations.NotNull;

public class RakuRegexSigspaceImpl extends ASTWrapperPsiElement implements RakuRegexSigspace {
    public RakuRegexSigspaceImpl(@NotNull ASTNode node) {
        super(node);
    }
}
