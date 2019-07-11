package edument.perl6idea.psi;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiTreeUtil;
import edument.perl6idea.filetypes.Perl6ModuleFileType;
import edument.perl6idea.psi.impl.Perl6PackageDeclImpl;
import edument.perl6idea.psi.symbols.*;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

import static edument.perl6idea.parsing.Perl6TokenTypes.UNV_WHITE_SPACE;

public interface Perl6PsiElement extends NavigatablePsiElement {
    /* Name-manages the enclosing file name into a module name, if possible.
     * Returns null if that's not possible or this doesn't seem to be a module. */
    default String getEnclosingPerl6ModuleName() {
        // Make sure it's a .pm6 file, and trim the extension.
        String path = getContainingFile().getVirtualFile().getPath();
        if (!path.endsWith(Perl6ModuleFileType.INSTANCE.getDefaultExtension()))
            return null;
        path = path.substring(0, path.length() - 4);

        // Make sure it's within the project and trim the project path
        // off the start.
        String projectPath = getProject().getBasePath();
        if (projectPath == null)
            return null;
        if (!path.startsWith(projectPath))
            return null;
        path = path.substring(projectPath.length() + 1);

        // Mangle it, removing a leading lib:: since lib/ is the standard place
        // for libraries.
        String libraryName = path.replaceAll("[/\\\\]", "::");
        return StringUtil.trimStart(libraryName, "lib::");
    }

    default Perl6Symbol resolveLexicalSymbol(Perl6SymbolKind kind, String name) {
        Perl6SingleResolutionSymbolCollector collector = new Perl6SingleResolutionSymbolCollector(name, kind);
        applyLexicalSymbolCollector(collector);
        return collector.getResult();
    }

    default Collection<Perl6Symbol> getLexicalSymbolVariants(Perl6SymbolKind... kinds) {
        Perl6VariantsSymbolCollector collector = new Perl6VariantsSymbolCollector(kinds);
        applyLexicalSymbolCollector(collector);
        return collector.getVariants();
    }

    default void applyExternalSymbolCollector(Perl6SymbolCollector collector) {
        Perl6PsiScope scope = PsiTreeUtil.getParentOfType(this, Perl6PsiScope.class);
        while (scope != null) {
            // If we are at top level already, we need to contribute CORE external symbols too
            if (scope instanceof Perl6File)
                scope.contributeScopeSymbols(collector);

            Perl6StatementList list = PsiTreeUtil.findChildOfType(scope, Perl6StatementList.class);
            if (list == null) return;
            Perl6Statement[] stats = PsiTreeUtil.getChildrenOfType(list, Perl6Statement.class);
            // Just go one level up, skipping the for loop below
            if (stats == null) stats = new Perl6Statement[0];
            for (Perl6Statement statement : stats) {
                // Do not iterate further If we already passed current element
                if (statement.getTextOffset() > this.getTextOffset()) break;
                for (PsiElement maybeImport : statement.getChildren()) {
                    if (!(maybeImport instanceof Perl6UseStatement || maybeImport instanceof Perl6NeedStatement)) continue;
                    Perl6LexicalSymbolContributor cont = (Perl6LexicalSymbolContributor)maybeImport;
                    cont.contributeLexicalSymbols(collector);
                    if (collector.isSatisfied()) return;
                }
            }
            scope = PsiTreeUtil.getParentOfType(scope, Perl6PsiScope.class);
        }
    }

    default void applyLexicalSymbolCollector(Perl6SymbolCollector collector) {
        Perl6PsiScope scope = PsiTreeUtil.getParentOfType(this, Perl6PsiScope.class);

        // XXX Is this relevant any more?
        // Avoid bottomless recursion:
        // If we are trying to resolve (hence apply) Perl6TypeName, the method may be called from class,
        // so `scope` points to this PackageDecl, and calling `contributeMOPSymbols` on that
        // will cycle itself.
        // But if is not a TypeName inside of Trait, we are safe to complete/resolve;
        //boolean insideOfTrait = getParent() instanceof Perl6Trait;
        //boolean packageTrait = false;
        //if (insideOfTrait) {
        //    packageTrait = getParent().getParent() instanceof Perl6PackageDecl || getParent().getParent() instanceof Perl6Also;
        //}
        //
        //if ((this instanceof Perl6TypeName || this instanceof Perl6IsTraitName) && packageTrait)
        //    scope = PsiTreeUtil.getParentOfType(scope, Perl6PsiScope.class);

        while (scope != null) {
            for (Perl6LexicalSymbolContributor cont : scope.getSymbolContributors()) {
                cont.contributeLexicalSymbols(collector);
                if (collector.isSatisfied())
                    return;
            }
            scope.contributeScopeSymbols(collector);
            if (collector.isSatisfied())
                return;
            scope = PsiTreeUtil.getParentOfType(scope, Perl6PsiScope.class);
        }
    }

    default String inferType() {
        return "Any";
    }

    @Nullable
    default PsiElement skipWhitespacesBackward() {
        PsiElement temp = getPrevSibling();
        while (temp != null &&
               (temp instanceof PsiWhiteSpace ||
                temp.getNode().getElementType() == UNV_WHITE_SPACE))
            temp = temp.getPrevSibling();
        return temp;
    }

    @Nullable
    default PsiElement skipWhitespacesForward() {
        PsiElement temp = getNextSibling();
        while (temp != null &&
               (temp instanceof PsiWhiteSpace ||
                temp.getNode().getElementType() == UNV_WHITE_SPACE))
            temp = temp.getNextSibling();
        return temp;
    }
}
