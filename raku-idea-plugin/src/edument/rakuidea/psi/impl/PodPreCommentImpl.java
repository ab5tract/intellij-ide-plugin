package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.PodPreComment;

public class PodPreCommentImpl extends ASTWrapperPsiElement implements PodPreComment {
    public PodPreCommentImpl(ASTNode node) {
        super(node);
    }
}
