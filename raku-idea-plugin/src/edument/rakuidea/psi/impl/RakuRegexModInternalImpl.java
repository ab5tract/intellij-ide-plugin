package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuRegexModInternal;
import org.jetbrains.annotations.NotNull;

public class RakuRegexModInternalImpl extends ASTWrapperPsiElement implements RakuRegexModInternal {
    public RakuRegexModInternalImpl(@NotNull ASTNode node) {
        super(node);
    }
}
