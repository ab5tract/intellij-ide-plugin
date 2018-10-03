package edument.perl6idea.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import edument.perl6idea.parsing.Perl6ElementTypes;
import edument.perl6idea.parsing.Perl6TokenTypes;
import edument.perl6idea.psi.Perl6MemberStubBasedPsi;
import edument.perl6idea.psi.Perl6Signature;
import edument.perl6idea.psi.Perl6RegexDecl;
import edument.perl6idea.psi.stub.Perl6RegexDeclStub;
import edument.perl6idea.psi.stub.Perl6RegexDeclStubElementType;
import edument.perl6idea.psi.symbols.Perl6ExplicitAliasedSymbol;
import edument.perl6idea.psi.symbols.Perl6ExplicitSymbol;
import edument.perl6idea.psi.symbols.Perl6SymbolCollector;
import edument.perl6idea.psi.symbols.Perl6SymbolKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Perl6RegexDeclImpl extends Perl6MemberStubBasedPsi<Perl6RegexDeclStub> implements Perl6RegexDecl {
    public Perl6RegexDeclImpl(@NotNull ASTNode node) {
        super(node);
    }

    public Perl6RegexDeclImpl(Perl6RegexDeclStub stub, Perl6RegexDeclStubElementType type) {
        super(stub, type);
    }

    @Override
    public String getRegexKind() {
        PsiElement declarator = findChildByType(Perl6TokenTypes.REGEX_DECLARATOR);
        return declarator == null ? "rule" : declarator.getText();
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return findChildByType(Perl6ElementTypes.LONG_NAME);
    }

    @Override
    public int getTextOffset() {
        PsiElement name = getNameIdentifier();
        return name == null ? 0 : name.getTextOffset();
    }

    @Override
    public String getName() {
        Perl6RegexDeclStub stub = getStub();
        if (stub != null)
            return stub.getRegexName();
        PsiElement name = getNameIdentifier();
        return name == null ? null : name.getText();
    }

    @Override
    public String getRegexName() {
        String name = getName();
        return name == null ? "<anon>" : name;
    }

    @Override
    public String getSignature() {
        return getRegexName() + summarySignature();
    }

    @Override
    public Perl6Signature getSignatureNode() {
        return findChildByClass(Perl6SignatureImpl.class);
    }

    @Override
    public String getReturnsTrait() {
        return null;
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        return null;
    }

    @Override
    public String presentableName() {
        return getName() + summarySignature();
    }

    @Override
    public String defaultScope() {
        return "has";
    }

    public String toString() {
        return getClass().getSimpleName() + "(Perl6:REGEX_DECLARATION)";
    }

    @Override
    public void contributeSymbols(Perl6SymbolCollector collector) {
        String scope = getScope();
        if (scope.equals("my") || scope.equals("our") || scope.equals("has")) {
            String name = getName();
            if (name != null) {
                collector.offerSymbol(new Perl6ExplicitSymbol(Perl6SymbolKind.Regex, this));
                collector.offerSymbol(new Perl6ExplicitSymbol(Perl6SymbolKind.Routine, this));
                collector.offerSymbol(new Perl6ExplicitAliasedSymbol(Perl6SymbolKind.Variable,
                    this, "&" + name));
            }
        }
    }
}
