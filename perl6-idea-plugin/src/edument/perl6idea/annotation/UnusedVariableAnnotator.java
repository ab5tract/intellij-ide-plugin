package edument.perl6idea.annotation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Query;
import edument.perl6idea.highlighter.Perl6Highlighter;
import edument.perl6idea.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UnusedVariableAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        // See if it's something we know how to handle, and extract the items to
        // search for and the search scope.
        List<PsiElement> toCheck = null;
        LocalSearchScope searchScope = null;
        String error = null;
        if (element instanceof Perl6VariableDecl) {
            // Variable declaration. Look for its scope.
            PsiElement maybeScopedDecl = element.getParent();
            if (!(maybeScopedDecl instanceof Perl6ScopedDecl))
                return;
            String scope = ((Perl6ScopedDecl)maybeScopedDecl).getScope();

            // If it's lexical or state, then we want to check for usages in the
            // declaring lexical scope.
            if (scope.equals("my") || scope.equals("state")) {
                Perl6PsiScope usageScope = PsiTreeUtil.getParentOfType(element, Perl6PsiScope.class);
                if (usageScope != null) {
                    searchScope = new LocalSearchScope(usageScope);
                    toCheck = new ArrayList<>();
                    for (Perl6Variable variable : ((Perl6VariableDecl)element).getVariables()) {
                        if (namedWithoutTwigil(variable.getName()))
                            continue;
                        toCheck.add(variable.getParent() instanceof Perl6ParameterVariable
                                    ? variable.getParent()
                                    : element);
                    }
                    error = "Unused variable";
                }
            }
        }
        else if (element instanceof Perl6ParameterVariable) {
            // Make sure the parameter is not really part of a signature in a
            // variable declaration; those are checked separately. Also ensure
            // it's not anonymous nor carrying a twigil, and not on a stubbed
            // method (where they can be just for documentation).
            String name = ((Perl6ParameterVariable)element).getName();
            if (namedWithoutTwigil(name))
                return;
            Perl6Signature signature = PsiTreeUtil.getParentOfType(element, Perl6Signature.class);
            if (signature != null) {
                PsiElement signatureOwner = signature.getParent();
                if (!(signatureOwner instanceof Perl6VariableDecl) &&
                        !(signatureOwner instanceof Perl6RoutineDecl && ((Perl6RoutineDecl)signatureOwner).isStubbed())) {
                    Perl6PsiScope usageScope = PsiTreeUtil.getParentOfType(element, Perl6PsiScope.class);
                    if (usageScope != null) {
                        searchScope = new LocalSearchScope(usageScope);
                        toCheck = Collections.singletonList(element);
                        error = "Unused parameter";
                    }
                }
            }
        }

        // Assuming we have search targets, go over them.
        if (toCheck == null)
            return;
        for (PsiElement expectedUsed : toCheck) {
            // We need two references, since the declaration resolves to itself.
            Query<PsiReference> results = ReferencesSearch.search(expectedUsed, searchScope);
            AtomicInteger count = new AtomicInteger();
            results.forEach(found -> count.incrementAndGet() < 2);

            // Annotate if not found.
            if (count.get() < 2) {
                holder.newAnnotation(HighlightSeverity.WEAK_WARNING, error)
                  .range(expectedUsed)
                  .textAttributes(Perl6Highlighter.UNUSED)
                  .create();
            }
        }
    }

    private static boolean namedWithoutTwigil(String name) {
        if (name == null || name.length() < 2)
            return true;
        char twigil = Perl6Variable.getTwigil(name);
        if (twigil != ' ')
            return true;
        return false;
    }
}
