package edument.rakuidea.cro.template.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.cro.template.psi.CroTemplateNumLiteral;
import org.jetbrains.annotations.NotNull;

public class CroTemplateNumLiteralImpl extends ASTWrapperPsiElement implements CroTemplateNumLiteral {
    public CroTemplateNumLiteralImpl(@NotNull ASTNode node) {
        super(node);
    }
}
