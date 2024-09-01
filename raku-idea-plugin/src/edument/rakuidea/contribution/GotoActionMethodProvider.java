package edument.rakuidea.contribution;

import com.intellij.navigation.GotoRelatedItem;
import com.intellij.navigation.GotoRelatedProvider;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuPackageDecl;
import edument.rakuidea.psi.RakuRegexDecl;
import edument.rakuidea.psi.RakuRoutineDecl;
import edument.rakuidea.psi.stub.index.RakuAllRoutinesStubIndex;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GotoActionMethodProvider extends GotoRelatedProvider {
    @NotNull
    @Override
    public List<? extends GotoRelatedItem> getItems(@NotNull PsiElement psiElement) {
        // Ensure that we are in a regex.
        RakuRegexDecl regex = PsiTreeUtil.getParentOfType(psiElement, RakuRegexDecl.class, false);
        if (regex == null)
            return Collections.emptyList();

        // Ensure that regex is inside of a grammar.
        RakuPackageDecl grammar = PsiTreeUtil.getParentOfType(regex, RakuPackageDecl.class);
        if (grammar == null || !grammar.getPackageKind().equals("grammar"))
            return Collections.emptyList();

        // Look for routines of the same name in the index.
        Project project = psiElement.getProject();
        Collection<RakuRoutineDecl> decls = RakuAllRoutinesStubIndex.getInstance()
            .get(regex.getRegexName(), project, GlobalSearchScope.allScope(project));

        // Take those that are in the same module as us.
        List<GotoRelatedItem> result = new ArrayList<>();
        ProjectFileIndex fileIndex = ProjectFileIndex.getInstance(project);
        Module module = fileIndex.getModuleForFile(regex.getContainingFile().getOriginalFile().getVirtualFile());
        for (RakuRoutineDecl maybeAction : decls) {
            if (maybeAction.getRoutineKind().equals("method")) {
                VirtualFile actionFile = maybeAction.getContainingFile().getOriginalFile().getVirtualFile();
                if (actionFile != null && fileIndex.getModuleForFile(actionFile) == module)
                    result.add(new GotoRelatedItem(maybeAction, "Action Method"));
            }
        }
        return result;
    }
}
