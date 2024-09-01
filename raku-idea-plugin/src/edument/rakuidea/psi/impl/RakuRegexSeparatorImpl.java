package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuRegexSeparator;
import org.jetbrains.annotations.NotNull;

public class RakuRegexSeparatorImpl extends ASTWrapperPsiElement implements RakuRegexSeparator {
    public RakuRegexSeparatorImpl(@NotNull ASTNode node) {
        super(node);
    }
}
