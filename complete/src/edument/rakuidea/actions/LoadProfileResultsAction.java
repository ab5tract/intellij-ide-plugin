package edument.rakuidea.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import edument.rakuidea.profiler.ProfileResultsChooserDialog;
import org.jetbrains.annotations.NotNull;

public class LoadProfileResultsAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        new ProfileResultsChooserDialog(e.getProject()).show();
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabled(e.getProject() != null);
    }
}
