package edument.rakuidea.actions;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.refactoring.RefactoringActionHandler;
import com.intellij.refactoring.actions.BasePlatformRefactoringAction;
import edument.rakuidea.refactoring.RakuExtractRegexPartHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExtractRegexAction extends BasePlatformRefactoringAction {
    @Nullable
    @Override
    protected RefactoringActionHandler getRefactoringHandler(@NotNull RefactoringSupportProvider provider) {
        return new RakuExtractRegexPartHandler();
    }

    @Override
    protected boolean isAvailableInEditorOnly() {
        return true;
    }
}
