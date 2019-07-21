package edument.perl6idea.providers;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.navigation.GotoRelatedItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.NotNullFunction;
import com.intellij.util.containers.ContainerUtil;
import edument.perl6idea.Perl6Icons;
import edument.perl6idea.parsing.Perl6TokenTypes;
import edument.perl6idea.psi.Perl6PackageDecl;
import edument.perl6idea.psi.Perl6Trait;
import edument.perl6idea.psi.stub.index.Perl6GlobalTypeStubIndex;
import edument.perl6idea.psi.stub.index.Perl6IndexableType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInsight.navigation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Perl6LineMarkerProvider extends RelatedItemLineMarkerProvider {
    private static final NotNullFunction<PsiElement, Collection<? extends GotoRelatedItem>> PERL6_GOTO_RELATED_ITEM_PROVIDER =
        dom -> Collections.singletonList(new GotoRelatedItem(dom, "Perl 6"));

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo> result) {
        Perl6PackageDecl decl = isValidNavigationElement(element);
        if (decl == null) return;

        List<PsiElement> targets = new ArrayList<>();

        targets.addAll(decl.collectParents());
        targets.addAll(decl.collectChildren());

        if (targets.size() > 0)
            result.add(NavigationGutterIconBuilder
                           .create(Perl6Icons.CLASS, ContainerUtil::createMaybeSingletonList, PERL6_GOTO_RELATED_ITEM_PROVIDER)
                           .setTargets(targets)
                           .setTooltipText("Navigate to subtypes and supertypes")
                           .createLineMarkerInfo(element)
            );
    }

    @Nullable
    private static Perl6PackageDecl isValidNavigationElement(@NotNull PsiElement element) {
        // If it is not a type name, we don't annotate it
        if (element.getNode().getElementType() != Perl6TokenTypes.NAME)
            return null;
        // If it is a type name inside of trait, we don't annotate it
        if (PsiTreeUtil.getParentOfType(element, Perl6Trait.class) != null)
            return null;
        // Return an outer package for the type name we are working with
        PsiElement nameParent = element.getParent();
        return nameParent instanceof Perl6PackageDecl ? (Perl6PackageDecl)nameParent : null;
    }
}
