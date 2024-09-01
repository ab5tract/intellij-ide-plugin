package edument.rakuidea.structureView;

import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.Strings;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import edument.rakuidea.RakuLanguage;
import edument.rakuidea.psi.*;
import edument.rakuidea.psi.RakuVariableDecl;
import edument.rakuidea.psi.RakuRoutineDecl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class RakuStructureAwareNavbar extends StructureAwareNavBarModelExtension {
    @Override
    public @Nullable String getPresentableText(Object object) {
        // Process Raku declarations
        if (object instanceof RakuPackageDecl) {
            return ((RakuPackageDecl)object).getPackageName();
        }
        else if (object instanceof RakuRoutineDecl) {
            return ((RakuRoutineDecl)object).getRoutineName();
        }
        else if (object instanceof RakuVariableDecl) {
            return Strings.join(Arrays.asList(((RakuVariableDecl)object).getVariableNames()), ", ");
        }
        else if (object instanceof RakuSubCall) {
            return ((RakuSubCall)object).getCallName();
        }
        // Common structure elements: files, directories etc.
        else if (object instanceof PsiFile) {
            return ((PsiFile)object).getName();
        }
        else if (object instanceof PsiDirectory)
            return null;
        else if (object instanceof Project) {
            return ((Project)object).getName();
        }
        return null;
    }

    @NotNull
    @Override
    protected Language getLanguage() {
        return RakuLanguage.INSTANCE;
    }
}
