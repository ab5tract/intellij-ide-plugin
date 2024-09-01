package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import edument.rakuidea.parsing.RakuTokenTypes;
import edument.rakuidea.psi.RakuMultiDecl;
import org.jetbrains.annotations.NotNull;

public class RakuMultiDeclImpl extends ASTWrapperPsiElement implements RakuMultiDecl {
    public RakuMultiDeclImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getMultiness() {
        PsiElement multiness = findChildByType(RakuTokenTypes.MULTI_DECLARATOR);
        return multiness == null ? "only" : multiness.getText();
    }
}
