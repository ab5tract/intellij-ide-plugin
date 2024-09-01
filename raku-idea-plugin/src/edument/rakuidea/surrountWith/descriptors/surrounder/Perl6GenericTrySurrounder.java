package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.*;

public abstract class Perl6GenericTrySurrounder<T extends RakuControl> extends Perl6Surrounder<T> {
    public Perl6GenericTrySurrounder(boolean isStatement) {
        super(isStatement);
    }

    protected abstract String createBranch();

    @Override
    protected T createElement(Project project) {
        T perl6Try = (T)RakuElementFactory.createTryStatement(project);
        RakuCatchStatement catchBlock = RakuElementFactory.createCatchStatement(project);
        RakuStatement statement = RakuElementFactory.createStatementFromText(project, createBranch());
        catchBlock.addStatements(new PsiElement[]{statement});
        perl6Try.addStatements(new PsiElement[]{catchBlock});
        return perl6Try;
    }

    @Override
    protected PsiElement insertStatements(T surrounder, PsiElement[] statements) {
        RakuCatchStatement catchStatement = PsiTreeUtil.findChildOfType(surrounder, RakuCatchStatement.class);
        RakuStatementList statementList = PsiTreeUtil.findChildOfType(surrounder, RakuStatementList.class);
        if (statementList == null || catchStatement == null)
            return null;
        for (PsiElement statement : statements) {
            statementList.addBefore(statement, catchStatement);
        }
        return null;
    }

    @Override
    protected PsiElement getAnchor(T surrounder) {
        return null;
    }

    @Override
    protected boolean isControl() {
        return false;
    }
}
