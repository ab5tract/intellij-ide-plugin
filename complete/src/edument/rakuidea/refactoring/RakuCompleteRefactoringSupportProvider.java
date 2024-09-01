package edument.rakuidea.refactoring;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.RefactoringActionHandler;
import edument.rakuidea.psi.RakuParameterVariable;
import edument.rakuidea.psi.RakuVariableDecl;
import edument.rakuidea.refactoring.introduce.constant.RakuIntroduceConstantHandler;
import edument.rakuidea.refactoring.introduce.variable.RakuIntroduceVariableHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RakuCompleteRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        return element instanceof RakuVariableDecl || element instanceof RakuParameterVariable;
    }

    @Nullable
    @Override
    public RefactoringActionHandler getIntroduceVariableHandler() {
        return new RakuIntroduceVariableHandler(null, "Extract Variable");
    }

    @Nullable
    @Override
    public RefactoringActionHandler getIntroduceConstantHandler() {
        return new RakuIntroduceConstantHandler(null, "Extract Constant");
    }

    @Nullable
    @Override
    public RefactoringActionHandler getExtractMethodHandler() {
        return new RakuExtractCodeBlockHandler(RakuCodeBlockType.METHOD);
    }

    @Override
    public boolean isInplaceIntroduceAvailable(@NotNull PsiElement element, PsiElement context) {
        return true;
    }
}
