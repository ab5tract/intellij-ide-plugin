package edument.perl6idea.annotation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import edument.perl6idea.annotation.fix.MissingModuleFix;
import edument.perl6idea.psi.Perl6ModuleName;
import edument.perl6idea.utils.Perl6ModuleListFetcher;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UsedModuleAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof Perl6ModuleName))
            return;

        String moduleName = element.getText();
        // We don't need to annotate late-bound modules
        if (moduleName.startsWith("::")) return;
        if (Perl6ModuleListFetcher.PREINSTALLED_MODULES.contains(moduleName))
            return;
        if (Perl6ModuleListFetcher.PRAGMAS.contains(moduleName))
            return;

        Project project = element.getProject();
        VirtualFile file = project.getBaseDir().findChild("META6.json");
        if (file == null) return;
        String jsonString;
        JSONObject meta;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())), StandardCharsets.UTF_8);
            meta = new JSONObject(jsonString);
        } catch (IOException|JSONException e) {
            return;
        }

        PsiReference ref = element.getReference();
        if (ref == null) return;
        PsiElement resolved = ref.resolve();
        if (resolved != null) return;

        if (meta.has("depends")) {
            JSONArray depends = (JSONArray)meta.get("depends");
            boolean inDepends = false;

            for (Object dependency : depends) {
                String[] parts = ((String)dependency).split("::");
                List<String> symbolParts = new ArrayList<>();
                for (String part : parts) {
                    int index = part.indexOf(':');
                    symbolParts.add(index == -1 ? part : part.substring(0, index));
                }
                String cleanedDependency = String.join("::", symbolParts);
                Set<String> provides = Perl6ModuleListFetcher.getProvidesByModuleAsync(element.getProject(), cleanedDependency);
                if (provides == null) return;
                inDepends = provides.contains(moduleName);
                if (inDepends) break;
            }

            if (!inDepends) {
                holder
                    .createErrorAnnotation(element, String.format("Cannot find %s based on dependencies from META6.json", moduleName))
                    .registerFix(new MissingModuleFix(project, moduleName));

            }
        }
    }
}
