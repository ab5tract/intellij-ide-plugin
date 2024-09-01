package edument.rakuidea.cro.template.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.cro.template.psi.CroTemplateHtmlComment;
import org.jetbrains.annotations.NotNull;

public class CroTemplateHtmlCommentImpl extends ASTWrapperPsiElement implements CroTemplateHtmlComment {
    public CroTemplateHtmlCommentImpl(@NotNull ASTNode node) {
        super(node);
    }
}
