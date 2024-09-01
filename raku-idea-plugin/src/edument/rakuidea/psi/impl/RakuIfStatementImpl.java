package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuIfStatement;
import edument.rakuidea.psi.RakuPointyBlock;
import edument.rakuidea.psi.RakuPsiElement;
import edument.rakuidea.psi.type.RakuType;
import org.jetbrains.annotations.NotNull;

import static edument.rakuidea.parsing.RakuTokenTypes.STATEMENT_CONTROL;

public class RakuIfStatementImpl extends ASTWrapperPsiElement implements RakuIfStatement {
    public RakuIfStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public String getLeadingStatementControl() {
        PsiElement control = findChildByType(STATEMENT_CONTROL);
        return control == null ? "" : control.getText();
    }

    @Override
    public boolean isTopicalizing() {
        return getLeadingStatementControl().equals("with") &&
               PsiTreeUtil.getChildOfType(this, RakuPointyBlock.class) == null;
    }

    @Override
    public RakuType inferTopicType() {
        // Condition is first non-token thing.
        RakuPsiElement condition = PsiTreeUtil.getChildOfType(this, RakuPsiElement.class);
        return condition == null ? RakuIfStatement.super.inferTopicType() : condition.inferType();
    }
}
