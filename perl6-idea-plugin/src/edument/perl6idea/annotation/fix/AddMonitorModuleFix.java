package edument.perl6idea.annotation.fix;

import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import edument.perl6idea.metadata.Perl6MetaDataComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

public class AddMonitorModuleFix implements IntentionAction {
    @Nls
    @NotNull
    @Override
    public String getText() {
        return "Use OO::Monitors module";
    }

    @Nls
    @NotNull
    @Override
    public String getFamilyName() {
        return "Raku";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
        return ModuleUtilCore.findModuleForFile(file) != null;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
        editor.getDocument().insertString(0, "use OO::Monitors;\n");
        Module module = ModuleUtilCore.findModuleForFile(file);
        assert module != null;
        Perl6MetaDataComponent metaData = module.getService(Perl6MetaDataComponent.class);
        if (!metaData.getDepends(true).contains("OO::Monitors")) {
            metaData.addDepends("OO::Monitors");
        }
    }

    @Override
    public boolean startInWriteAction() {
        return true;
    }
}
