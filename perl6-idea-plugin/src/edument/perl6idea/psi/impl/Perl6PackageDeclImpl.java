package edument.perl6idea.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.meta.PsiMetaData;
import com.intellij.psi.meta.PsiMetaOwner;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.Stub;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import edument.perl6idea.psi.*;
import edument.perl6idea.psi.stub.*;
import edument.perl6idea.psi.stub.index.Perl6GlobalTypeStubIndex;
import edument.perl6idea.psi.stub.index.Perl6IndexableType;
import edument.perl6idea.psi.stub.index.Perl6LexicalTypeStubIndex;
import edument.perl6idea.psi.symbols.*;
import edument.perl6idea.sdk.Perl6SdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static edument.perl6idea.parsing.Perl6TokenTypes.NAME;

public class Perl6PackageDeclImpl extends Perl6TypeStubBasedPsi<Perl6PackageDeclStub>
        implements Perl6PackageDecl, PsiMetaOwner {
    public Perl6PackageDeclImpl(@NotNull ASTNode node) {
        super(node);
    }

    public Perl6PackageDeclImpl(final Perl6PackageDeclStub stub, final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    @Override
    public String getPackageKind() {
        Perl6PackageDeclStub stub = getStub();
        if (stub != null)
            return stub.getPackageKind();

        PsiElement declarator = getDeclarator();
        return declarator == null ? "package" : declarator.getText();
    }

    @Override
    public String getPackageName() {
        return getName();
    }

    @Nullable
    @Override
    public PsiElement getPackageKeywordNode() {
        return getDeclarator();
    }

    public String toString() {
        return getClass().getSimpleName() + "(Perl6:PACKAGE_DECLARATION)";
    }

    @Override
    public void contributeScopeSymbols(Perl6SymbolCollector collector) {
        String packageName = getPackageName();
        if (packageName == null) return;
        collector.offerSymbol(new Perl6ExplicitAliasedSymbol(Perl6SymbolKind.Variable, this, "$?PACKAGE"));
        if (collector.isSatisfied()) return;
        switch (getPackageKind()) {
            case "class":
            case "grammar":
                collector.offerSymbol(new Perl6ExplicitAliasedSymbol(Perl6SymbolKind.Variable, this, "$?CLASS"));
                break;
            case "role":
                collector.offerSymbol(new Perl6ExplicitAliasedSymbol(Perl6SymbolKind.Variable, this, "$?ROLE"));
                collector.offerSymbol(new Perl6ImplicitSymbol(Perl6SymbolKind.Variable, "$?CLASS", this));
                break;
        }
    }

    @Override
    public void contributeLexicalSymbols(Perl6SymbolCollector collector) {
        super.contributeLexicalSymbols(collector);
        contributeNestedPackagesWithPrefix(collector, getPackageName() + "::");
    }

    @Override
    public void contributeMOPSymbols(Perl6SymbolCollector collector, MOPSymbolsAllowed symbolsAllowed) {
        contributeInternals(collector, symbolsAllowed);
        contributeFromElders(collector, symbolsAllowed);
    }

    // TODO Re-instate trusts support somehow
    private List<String> getTrusts() {
        List<String> trusts = new ArrayList<>();
        Perl6PackageDeclStub stub = getStub();
        if (stub != null) {
            stub.getChildrenStubs().stream()
                    .filter(s -> s instanceof Perl6TypeNameStub)
                    .forEach(s -> trusts.add(((Perl6TypeNameStub)s).getTypeName()));
        } else {
            Perl6StatementList statementList = PsiTreeUtil.findChildOfType(this, Perl6StatementList.class);
            if (statementList == null) return new ArrayList<>();
            for (PsiElement statement : statementList.getChildren()) {
                if (statement.getFirstChild() instanceof Perl6Trusts)
                    trusts.add(((Perl6Trusts) statement.getFirstChild()).getTypeName());
            }
        }
        return trusts;
    }

    private void contributeInternals(Perl6SymbolCollector collector, MOPSymbolsAllowed symbolsAllowed) {
        Perl6PackageDeclStub stub = getStub();
        if (stub != null) {
            for (StubElement nestedStub : stub.getChildrenStubs()) {
                if (nestedStub instanceof Perl6RoutineDeclStub) {
                    Perl6RoutineDeclStub declStub = (Perl6RoutineDeclStub)nestedStub;
                    if (declStub.isPrivate() && !symbolsAllowed.privateMethodsVisible)
                        continue;
                    if (declStub.getRoutineKind().equals("submethod") && !symbolsAllowed.submethodsVisible)
                        continue;
                    declStub.getPsi().contributeMOPSymbols(collector, symbolsAllowed);
                    if (collector.isSatisfied()) return;
                }
                else if (nestedStub instanceof Perl6ScopedDeclStub) {
                    Perl6ScopedDeclStub scopedVar = (Perl6ScopedDeclStub)nestedStub;
                    List<StubElement> stubsUnderScoped = scopedVar.getChildrenStubs();
                    for (StubElement var : stubsUnderScoped) {
                        if (var instanceof Perl6VariableDeclStub) {
                            Perl6VariableDeclStub declStub = (Perl6VariableDeclStub)var;
                            if (!declStub.getScope().equals("has"))
                                continue;
                            declStub.getPsi().contributeMOPSymbols(collector, symbolsAllowed);
                            if (collector.isSatisfied()) return;
                        }
                    }
                } else if (nestedStub instanceof Perl6RegexDeclStub) {
                    Perl6RegexDeclStub declStub = (Perl6RegexDeclStub)nestedStub;
                    declStub.getPsi().contributeMOPSymbols(collector, symbolsAllowed);
                    if (collector.isSatisfied()) return;
                }
            }
            return;
        }

        Perl6StatementList list = PsiTreeUtil.findChildOfType(this, Perl6StatementList.class);
        if (list == null) return;
        for (PsiElement child : list.getChildren()) {
            PsiElement firstChild = child.getFirstChild();
            if (firstChild instanceof Perl6RoutineDecl) {
                Perl6RoutineDecl decl = (Perl6RoutineDecl)firstChild;
                decl.contributeMOPSymbols(collector, symbolsAllowed);
            }
            else if (firstChild instanceof Perl6MultiDecl) {
                Perl6RoutineDecl maybeDecl = PsiTreeUtil.getChildOfType(firstChild, Perl6RoutineDecl.class);
                if (maybeDecl != null)
                    maybeDecl.contributeMOPSymbols(collector, symbolsAllowed);
            }
            else if (firstChild instanceof Perl6ScopedDecl) {
                Perl6ScopedDecl decl = (Perl6ScopedDecl)firstChild;
                if (decl.getScope().equals("has")) {
                    Perl6VariableDecl varDecl = PsiTreeUtil.getChildOfType(decl, Perl6VariableDecl.class);
                    if (varDecl != null)
                        varDecl.contributeMOPSymbols(collector, symbolsAllowed);
                }
            }
            else if (firstChild instanceof Perl6RegexDecl) {
                ((Perl6RegexDecl)firstChild).contributeMOPSymbols(collector, symbolsAllowed);
            }
            if (collector.isSatisfied()) return;
        }
    }

    private void contributeFromElders(Perl6SymbolCollector collector, MOPSymbolsAllowed symbolsAllowed) {
        Perl6PackageDeclStub stub = getStub();
        List<Pair<String, Perl6PackageDecl>> perl6PackageDecls = new ArrayList<>();
        List<Pair<String, String>> externals = new ArrayList<>();
        boolean isAny = true;
        boolean isMu = true;
        boolean isGrammar = getPackageKind().equals("grammar");

        if (stub != null) {
            List<StubElement> children = stub.getChildrenStubs();
            for (StubElement child : children) {
                if (!(child instanceof Perl6TraitStub)) continue;
                Perl6TraitStub traitStub = (Perl6TraitStub)child;
                if (!traitStub.getTraitModifier().equals("does") && !traitStub.getTraitModifier().equals("is")) continue;
                String name = traitStub.getTraitName();
                Project project = getProject();
                List<Perl6IndexableType> indexables = new ArrayList<>();
                indexables.addAll(Perl6LexicalTypeStubIndex.getInstance().get(name, project,
                        GlobalSearchScope.projectScope(project)));
                indexables.addAll(Perl6GlobalTypeStubIndex.getInstance().get(name, project,
                        GlobalSearchScope.projectScope(project)));
                if (indexables.size() == 1) {
                    Perl6PackageDecl decl = (Perl6PackageDecl) indexables.get(0);
                    perl6PackageDecls.add(Pair.create(traitStub.getTraitModifier(), decl));
                } else {
                    externals.add(Pair.create(traitStub.getTraitModifier(), name));
                }
                isAny = !name.equals("Mu");
            }
        }
        else {
            for (Perl6Trait trait : getTraits()) {
                if (!(trait.getTraitModifier().equals("does") || trait.getTraitModifier().equals("is"))) continue;
                PsiElement element = trait.getTraitModifier().equals("does") ?
                                     PsiTreeUtil.findChildOfType(trait, Perl6TypeName.class) :
                                     PsiTreeUtil.findChildOfType(trait, Perl6IsTraitName.class);
                if (element == null) continue;
                PsiReference ref = element.getReference();
                if (ref == null) continue;
                PsiElement decl = ref.resolve();
                if (decl != null)
                    perl6PackageDecls.add(Pair.create(trait.getTraitModifier(), (Perl6PackageDecl)decl));
                else
                    externals.add(Pair.create(trait.getTraitModifier(), trait.getTraitName()));
                if (trait.getTraitName().equals("Mu"))
                    isAny = false;
            }
        }

        if (isAny)
            for (String method : Perl6SdkType.getInstance().getCoreSettingSymbol("Any", this).methods()) {
                collector.offerSymbol(new Perl6ExternalSymbol(Perl6SymbolKind.Method, '.' + method));
                if (collector.isSatisfied()) return;
            }
        if (isMu)
            for (String method : Perl6SdkType.getInstance().getCoreSettingSymbol("Mu", this).methods()) {
                collector.offerSymbol(new Perl6ExternalSymbol(Perl6SymbolKind.Method, '.' + method));
                if (collector.isSatisfied()) return;
            }
        if (isGrammar)
            for (String method : Perl6SdkType.getInstance().getCoreSettingSymbol("Cursor", this).methods()) {
                collector.offerSymbol(new Perl6ExternalSymbol(Perl6SymbolKind.Method, '.' + method));
                if (collector.isSatisfied()) return;
            }

        for (Pair<String, Perl6PackageDecl> pair : perl6PackageDecls) {
            // Local perl6PackageDecl
            Perl6PackageDecl typeRef = pair.second;
            String mod = pair.first;
            boolean isDoes = mod.equals("does");
            typeRef.contributeMOPSymbols(collector, isDoes ? symbolsAllowed.does() : symbolsAllowed.is());
            typeRef.contributeScopeSymbols(collector);
            if (collector.isSatisfied()) return;
        }
        for (Pair<String, String> pair : externals) {
            // It can be either external perl6PackageDecl or non-existent one
            boolean isDoes = pair.first.equals("does");
            String extType = pair.second;
            // Chop off possible parametrized roles
            int index = extType.indexOf('[');
            if (index != -1)
                extType = extType.substring(0, index);
            contributeExternalPackage(collector, extType, isDoes ? symbolsAllowed.does() : symbolsAllowed.is());
            if (collector.isSatisfied()) return;
        }
    }

    private void contributeExternalPackage(Perl6SymbolCollector collector, String typeName,
                                           MOPSymbolsAllowed symbolsAllowed) {
        Perl6VariantsSymbolCollector extCollector =
                new Perl6VariantsSymbolCollector(Perl6SymbolKind.ExternalPackage);
        applyExternalSymbolCollector(extCollector);
        for (Perl6Symbol pack : extCollector.getVariants()) {
            Perl6ExternalPackage externalPackage = (Perl6ExternalPackage)pack;
            if (!(pack.getName().equals(typeName)))
                continue;
            externalPackage.contributeMOPSymbols(collector, symbolsAllowed);
        }
    }

    @Override
    public void contributeNestedPackagesWithPrefix(Perl6SymbolCollector collector, String prefix) {
        // Walk to find immediately nested packages, but not those within them
        // (we make a recursive contribute call on those).
        Perl6PackageDeclStub stub = getStub();
        if (stub != null) {
            contributeNestedPackagesWithPrefixStub(collector, prefix, stub);
        }
        else {
            contributeNestedPackagesWithPrefixNonStub(collector, prefix);
        }
    }

    private void contributeNestedPackagesWithPrefixNonStub(Perl6SymbolCollector collector, String prefix) {
        Queue<Perl6PsiElement> visit = new LinkedList<>();
        visit.add(this);
        while (!visit.isEmpty()) {
            Perl6PsiElement current = visit.remove();
            boolean addChildren = false;
            if (current == this) {
                addChildren = true;
            }
            else if (current instanceof Perl6PackageDecl) {
                Perl6PackageDecl nested = (Perl6PackageDecl)current;
                if (nested.getScope().equals("our")) {
                    String nestedName = nested.getPackageName();
                    if (nestedName != null && !nestedName.isEmpty()) {
                        collector.offerSymbol(new Perl6ExplicitAliasedSymbol(Perl6SymbolKind.TypeOrConstant,
                                                                             nested, prefix + nestedName));
                        if (collector.isSatisfied()) return;
                        nested.contributeNestedPackagesWithPrefix(collector, prefix + nestedName + "::");
                    }
                }
            }
            else {
                addChildren = true;
            }
            if (addChildren)
                for (PsiElement e : current.getChildren())
                    if (e instanceof Perl6PsiElement)
                        visit.add((Perl6PsiElement)e);
        }
    }

    private static void contributeNestedPackagesWithPrefixStub(Perl6SymbolCollector collector, String prefix, Perl6PackageDeclStub stub) {
        Queue<Stub> visit = new LinkedList<>();
        visit.add(stub);
        while (!visit.isEmpty()) {
            Stub current = visit.remove();
            boolean addChildren = false;
            if (current == stub) {
                addChildren = true;
            }
            else if (current instanceof Perl6PackageDeclStub) {
                Perl6PackageDeclStub nested = (Perl6PackageDeclStub)current;
                if (nested.getScope().equals("our")) {
                    String nestedName = nested.getTypeName();
                    if (nestedName != null && !nestedName.isEmpty()) {
                        Perl6PackageDecl psi = nested.getPsi();
                        collector.offerSymbol(new Perl6ExplicitAliasedSymbol(Perl6SymbolKind.TypeOrConstant,
                                                                             psi, prefix + nestedName));
                        if (collector.isSatisfied()) return;
                        psi.contributeNestedPackagesWithPrefix(collector, prefix + nestedName + "::");
                    }
                }
            }
            else {
                addChildren = true;
            }
            if (addChildren)
                visit.addAll(current.getChildrenStubs());
        }
    }

    @Nullable
    @Override
    public PsiMetaData getMetaData() {
        PsiElement decl = this;
        String shortName = getPackageName();
        if (shortName == null)
            shortName = "";
        int lastIndexOf = shortName.lastIndexOf(':');
        if (lastIndexOf != -1) {
            shortName = shortName.substring(lastIndexOf + 1);
        }
        String finalShortPackageName = shortName;
        return new PsiMetaData() {
            @Override
            public PsiElement getDeclaration() {
                return decl;
            }

            @Override
            public String getName(PsiElement context) {
                return finalShortPackageName;
            }

            @Override
            public String getName() {
                return finalShortPackageName;
            }

            @Override
            public void init(PsiElement element) {
            }

            @NotNull
            @Override
            public Object[] getDependences() {
                return ArrayUtil.EMPTY_OBJECT_ARRAY;
            }
        };
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        PsiElement nameElement = Perl6ElementFactory
            .createTypeDeclarationName(getProject(), name);
        ASTNode keyNode = findChildByType(NAME);
        if (keyNode != null) {
            ASTNode newKeyNode = nameElement.getNode();
            getNode().replaceChild(keyNode, newKeyNode);
        }
        return this;
    }
}
