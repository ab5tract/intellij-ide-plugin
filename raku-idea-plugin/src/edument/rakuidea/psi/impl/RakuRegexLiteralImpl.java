package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuRegexLiteral;
import org.jetbrains.annotations.NotNull;

public class RakuRegexLiteralImpl extends ASTWrapperPsiElement implements RakuRegexLiteral {
    public RakuRegexLiteralImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public boolean mightMatchZeroWidth() {
        return getTextLength() == 0;
    }
}
