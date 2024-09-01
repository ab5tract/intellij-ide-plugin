package edument.rakuidea.refactoring;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.RefactoringActionHandler;
import edument.rakuidea.psi.RakuParameterVariable;
import edument.rakuidea.psi.RakuVariableDecl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class RakuCommunityRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        return element instanceof RakuVariableDecl || element instanceof RakuParameterVariable;
    }

    @Nullable
    @Override
    public RefactoringActionHandler getIntroduceVariableHandler() {
        return null;
    }

    @Nullable
    @Override
    public RefactoringActionHandler getIntroduceConstantHandler() {
        return null;
    }

    @Nullable
    @Override
    public RefactoringActionHandler getExtractMethodHandler() {
        return null;
    }

    @Override
    public boolean isInplaceIntroduceAvailable(@NotNull PsiElement element, PsiElement context) {
        return true;
    }
}
