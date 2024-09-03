package edument.rakuidea.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import edument.rakuidea.psi.*;
import edument.rakuidea.psi.stub.RakuConstantStub;
import edument.rakuidea.psi.stub.RakuConstantStubElementType;
import edument.rakuidea.psi.symbols.RakuExplicitAliasedSymbol;
import edument.rakuidea.psi.symbols.RakuExplicitSymbol;
import edument.rakuidea.psi.symbols.RakuSymbolCollector;
import edument.rakuidea.psi.symbols.RakuSymbolKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RakuConstantImpl extends RakuMemberStubBasedPsi<RakuConstantStub> implements RakuConstant {
    public RakuConstantImpl(@NotNull ASTNode node) {
        super(node);
    }

    public RakuConstantImpl(RakuConstantStub stub, RakuConstantStubElementType type) {
        super(stub, type);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        RakuVariable varNode = findChildByClass(RakuVariable.class);
        return varNode != null
               ? varNode.getVariableToken()
               : findChildByClass(RakuTermDefinition.class);
    }

    @Override
    public String getName() {
        RakuConstantStub stub = this.getStub();
        if (stub != null)
            return stub.getConstantName();
        PsiElement nameIdent = getNameIdentifier();
        return nameIdent != null ? nameIdent.getText() : null;
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        return null;
    }

    @Override
    public String getConstantName() {
        return getName();
    }

    @Override
    public String defaultScope() {
        return "our";
    }

    public String toString() {
        return getClass().getSimpleName() + "(Raku:CONSTANT)";
    }

    @Override
    public void contributeLexicalSymbols(RakuSymbolCollector collector) {
        String name = getName();
        if (name != null && !name.isEmpty()) {
            char sigil = name.charAt(0);
            if (sigil == '$' || sigil == '@' || sigil == '%' || sigil == '&') {
                collector.offerSymbol(new RakuExplicitSymbol(RakuSymbolKind.Variable, this));
                if (sigil == '&')
                    collector.offerSymbol(new RakuExplicitAliasedSymbol(RakuSymbolKind.Routine, this,
                                                                        name.substring(1)));
            }
            else {
                collector.offerSymbol(new RakuExplicitSymbol(RakuSymbolKind.TypeOrConstant, this));
                if (!collector.isSatisfied()) {
                    String globalName = getGlobalName();
                    if (globalName != null)
                        collector.offerSymbol(new RakuExplicitAliasedSymbol(RakuSymbolKind.TypeOrConstant,
                                                                            this, globalName));
                }
            }
        }
    }
}
