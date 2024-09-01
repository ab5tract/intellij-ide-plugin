package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuComment;
import org.jetbrains.annotations.NotNull;

public class RakuCommentImpl extends ASTWrapperPsiElement implements RakuComment {
    public RakuCommentImpl(@NotNull ASTNode node) {
        super(node);
    }
}
