package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuGivenStatement;
import edument.rakuidea.psi.RakuPointyBlock;
import edument.rakuidea.psi.RakuPsiElement;
import edument.rakuidea.psi.type.RakuType;
import org.jetbrains.annotations.NotNull;

public class RakuGivenStatementImpl extends ASTWrapperPsiElement implements RakuGivenStatement {
    public RakuGivenStatementImpl(@NotNull ASTNode node) {
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
        return condition == null ? RakuGivenStatement.super.inferTopicType() : condition.inferType();
    }
}
