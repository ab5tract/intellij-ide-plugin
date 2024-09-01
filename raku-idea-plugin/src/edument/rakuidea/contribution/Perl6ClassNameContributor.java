package edument.rakuidea.contribution;

import com.intellij.navigation.ChooseByNameContributor;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.ArrayUtil;
import edument.rakuidea.psi.stub.index.RakuGlobalTypeStubIndex;
import edument.rakuidea.psi.stub.index.RakuLexicalTypeStubIndex;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@InternalIgnoreDependencyViolation
public class Perl6ClassNameContributor implements ChooseByNameContributor {
    @Override
    public String @NotNull [] getNames(Project project, boolean includeNonProjectItems) {
        List<String> result = new ArrayList<>();
        result.addAll(RakuGlobalTypeStubIndex.getInstance().getAllKeys(project));
        result.addAll(RakuLexicalTypeStubIndex.getInstance().getAllKeys(project));
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

        return results.toArray(NavigationItem.EMPTY_NAVIGATION_ITEM_ARRAY);
    }
}
