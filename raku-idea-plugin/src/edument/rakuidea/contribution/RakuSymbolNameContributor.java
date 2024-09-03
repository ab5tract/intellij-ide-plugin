package edument.rakuidea.contribution;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.ArrayUtil;
import edument.rakuidea.extensions.RakuFrameworkCall;
import edument.rakuidea.psi.stub.index.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@InternalIgnoreDependencyViolation
public class RakuSymbolNameContributor implements ChooseByNameContributor {
    @Override
    public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
        List<String> result = new ArrayList<>();
        result.addAll(RakuGlobalTypeStubIndex.getInstance().getAllKeys(project));
        result.addAll(RakuLexicalTypeStubIndex.getInstance().getAllKeys(project));
        result.addAll(RakuAllRoutinesStubIndex.getInstance().getAllKeys(project));
        result.addAll(RakuAllRegexesStubIndex.getInstance().getAllKeys(project));
        result.addAll(RakuAllAttributesStubIndex.getInstance().getAllKeys(project));
        result.addAll(RakuAllConstantsStubIndex.getInstance().getAllKeys(project));

        RakuFrameworkCall[] extensions = RakuFrameworkCall.EP_NAME.getExtensions();
        for (RakuFrameworkCall ext : extensions)
            ext.contributeSymbolNames(project, result);

        return ArrayUtil.toStringArray(result);
    }

    @Override
    public NavigationItem @NotNull [] getItemsByName(String name, String pattern, Project project, boolean includeNonProjectItems) {
        List<NavigationItem> results = new ArrayList<>();

        RakuGlobalTypeStubIndex globalIndex = RakuGlobalTypeStubIndex.getInstance();
        for (String globalType : Filtering.typeMatch(globalIndex.getAllKeys(project), pattern))
            results.addAll(globalIndex.get(globalType, project, GlobalSearchScope.projectScope(project)));

        RakuLexicalTypeStubIndex lexicalIndex = RakuLexicalTypeStubIndex.getInstance();
        for (String lexicalType : Filtering.typeMatch(lexicalIndex.getAllKeys(project), pattern))
            results.addAll(lexicalIndex.get(lexicalType, project, GlobalSearchScope.projectScope(project)));

        RakuAllRoutinesStubIndex routinesIndex = RakuAllRoutinesStubIndex.getInstance();
        for (String routine : Filtering.simpleMatch(routinesIndex.getAllKeys(project), pattern))
            results.addAll(routinesIndex.get(routine, project, GlobalSearchScope.projectScope(project)));

        RakuAllRegexesStubIndex regexesIndex = RakuAllRegexesStubIndex.getInstance();
        for (String regex : Filtering.simpleMatch(regexesIndex.getAllKeys(project), pattern))
            results.addAll(regexesIndex.get(regex, project, GlobalSearchScope.projectScope(project)));

        RakuAllAttributesStubIndex attrIndex = RakuAllAttributesStubIndex.getInstance();
        for (String attr : Filtering.variableMatch(attrIndex.getAllKeys(project), pattern))
            results.addAll(attrIndex.get(attr, project, GlobalSearchScope.projectScope(project)));

        RakuAllConstantsStubIndex constantIndex = RakuAllConstantsStubIndex.getInstance();
        for (String constant : Filtering.simpleMatch(constantIndex.getAllKeys(project), pattern))
            results.addAll(constantIndex.get(constant, project, GlobalSearchScope.projectScope(project)));

        RakuFrameworkCall[] extensions = RakuFrameworkCall.EP_NAME.getExtensions();
        for (RakuFrameworkCall ext : extensions)
            ext.contributeSymbolItems(project, pattern, results);

        return results.toArray(NavigationItem.EMPTY_NAVIGATION_ITEM_ARRAY);
    }
}
