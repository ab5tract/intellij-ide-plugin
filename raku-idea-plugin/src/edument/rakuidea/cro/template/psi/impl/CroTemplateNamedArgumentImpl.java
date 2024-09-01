package edument.rakuidea.cro.template.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.cro.template.psi.CroTemplateNamedArgument;
import org.jetbrains.annotations.NotNull;

public class CroTemplateNamedArgumentImpl extends ASTWrapperPsiElement implements CroTemplateNamedArgument {
    public CroTemplateNamedArgumentImpl(@NotNull ASTNode node) {
        super(node);
    }
}
