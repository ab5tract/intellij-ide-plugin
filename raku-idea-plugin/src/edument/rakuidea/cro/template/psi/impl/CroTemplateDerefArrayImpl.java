package edument.rakuidea.cro.template.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.cro.template.psi.CroTemplateDerefArray;
import org.jetbrains.annotations.NotNull;

public class CroTemplateDerefArrayImpl extends ASTWrapperPsiElement implements CroTemplateDerefArray {
    public CroTemplateDerefArrayImpl(@NotNull ASTNode node) {
        super(node);
    }
}
