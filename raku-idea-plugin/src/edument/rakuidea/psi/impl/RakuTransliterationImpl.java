package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuTransliteration;
import org.jetbrains.annotations.NotNull;

public class RakuTransliterationImpl extends ASTWrapperPsiElement implements RakuTransliteration {
    public RakuTransliterationImpl(@NotNull ASTNode node) {
        super(node);
    }
}
