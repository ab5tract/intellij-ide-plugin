package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuPointyBlock;
import edument.rakuidea.psi.RakuPsiElement;
import edument.rakuidea.psi.RakuWithoutStatement;
import edument.rakuidea.psi.type.RakuType;
import org.jetbrains.annotations.NotNull;

public class RakuWithoutStatementImpl extends ASTWrapperPsiElement implements RakuWithoutStatement {
    public RakuWithoutStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public boolean isTopicalizing() {
        return PsiTreeUtil.getChildOfType(this, RakuPointyBlock.class) == null;
    }

    @Override
    public RakuType inferTopicType() {
        // Condition is first non-token thing.
        RakuPsiElement condition = PsiTreeUtil.getChildOfType(this, RakuPsiElement.class);
        return condition == null ? RakuWithoutStatement.super.inferTopicType() : condition.inferType();
    }
}
