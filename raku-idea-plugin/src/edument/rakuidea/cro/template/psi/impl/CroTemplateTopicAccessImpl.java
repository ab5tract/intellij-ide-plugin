package edument.rakuidea.cro.template.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.cro.template.psi.CroTemplateTopicAccess;
import org.jetbrains.annotations.NotNull;

public class CroTemplateTopicAccessImpl extends ASTWrapperPsiElement implements CroTemplateTopicAccess {
    public CroTemplateTopicAccessImpl(@NotNull ASTNode node) {
        super(node);
    }
}
