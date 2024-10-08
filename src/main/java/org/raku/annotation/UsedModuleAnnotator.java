package org.raku.annotation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import org.raku.annotation.fix.CreateLocalModuleFix;
import org.raku.annotation.fix.MissingModuleFix;
import org.raku.metadata.RakuMetaDataComponent;
import org.raku.psi.RakuColonPair;
import org.raku.psi.RakuLongName;
import org.raku.psi.RakuModuleName;
import org.raku.utils.RakuModuleListFetcher;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class UsedModuleAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof RakuModuleName))
            return;

        RakuLongName moduleNameNode = PsiTreeUtil.findChildOfType(element, RakuLongName.class);
        if (moduleNameNode == null)
            return;
        String moduleName = moduleNameNode.getFirstChild().getText();

        Collection<RakuColonPair> params = PsiTreeUtil.findChildrenOfType(moduleNameNode, RakuColonPair.class);
        for (RakuColonPair colonPair : params) {
            String key = colonPair.getKey();
            if (Objects.equals(key, "from"))
                return;
        }

        // We don't need to annotate late-bound modules
        if (moduleName.startsWith("::")) return;
        if (RakuModuleListFetcher.PREINSTALLED_MODULES.contains(moduleName))
            return;
        if (RakuModuleListFetcher.PRAGMAS.contains(moduleName))
            return;

        Module module = ModuleUtilCore.findModuleForPsiElement(element);
        if (module == null) return;

        RakuMetaDataComponent metaData = module.getService(RakuMetaDataComponent.class);

        // No need to annotate "missing" modules, if there are
        // no META data available
        if (!metaData.isMetaDataExist())
            return;

        PsiReference ref = element.getReference();
        if (ref == null) return;
        PsiElement resolved = ref.resolve();
        if (resolved != null) return;

        Project project = element.getProject();

        if (!RakuModuleListFetcher.isReady()) {
            RakuModuleListFetcher.populateModules(project);
            return;
        }

        List<String> dependencies = new ArrayList<>();
        dependencies.addAll(metaData.getDepends(true));
        dependencies.addAll(metaData.getTestDepends(true));
        dependencies.addAll(metaData.getBuildDepends(true));
        for (String dependency : dependencies) {
            Set<String> providesOfDependency = RakuModuleListFetcher.getProvidesByModule(project, dependency, new HashSet<>());
            // Maybe it is a part of the distribution, and we can get something out its parent distribution
            if (providesOfDependency.isEmpty()) {
                String parentModuleName = RakuModuleListFetcher.getModuleByProvide(project, dependency);
                if (parentModuleName != null) {
                    providesOfDependency = RakuModuleListFetcher.getProvidesByModule(project, parentModuleName, new HashSet<>());
                }
            }
            // If a module is in dependencies list, do nothing
            if (providesOfDependency.contains(moduleName))
                return;
        }
        String holderPackage = RakuModuleListFetcher.getModuleByProvide(project, moduleName);
        if (holderPackage != null) {
            holder
                .newAnnotation(HighlightSeverity.ERROR, String.format("Cannot find %s based on dependencies from META6.json", moduleName))
                .range(element)
                .withFix(new MissingModuleFix(holderPackage)).create();
        }
        else {
            holder
                .newAnnotation(HighlightSeverity.ERROR, String.format("Cannot find %s in the ecosystem", moduleName))
                .range(element)
                .withFix(new CreateLocalModuleFix(module, (RakuModuleName)element)).create();
        }
    }
}
