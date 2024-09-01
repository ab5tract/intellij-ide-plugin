package edument.rakuidea.actions;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.refactoring.actions.BasePlatformRefactoringAction;
import edument.rakuidea.refactoring.RakuCodeBlockType;
import edument.rakuidea.refactoring.RakuExtractCodeBlockHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtractRoutineAction extends BasePlatformRefactoringAction {
    public ExtractRoutineAction() {
        setInjectedContext(true);
    }

    @Nullable
    @Override
    protected RefactoringActionHandler getRefactoringHandler(@NotNull RefactoringSupportProvider provider) {
        return new RakuExtractCodeBlockHandler(RakuCodeBlockType.ROUTINE);
    }

    @Override
    protected boolean isAvailableInEditorOnly() {
        return true;
    }
}
