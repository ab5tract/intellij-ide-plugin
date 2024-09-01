package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import edument.rakuidea.highlighter.RakuHighlightVisitor;
import edument.rakuidea.parsing.RakuTokenTypes;
import edument.rakuidea.psi.RakuParameter;
import edument.rakuidea.psi.RakuPointyBlock;
import edument.rakuidea.psi.RakuSignature;
import edument.rakuidea.psi.symbols.RakuImplicitSymbol;
import edument.rakuidea.psi.symbols.RakuSymbolCollector;
import edument.rakuidea.psi.symbols.RakuSymbolKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RakuPointyBlockImpl extends ASTWrapperPsiElement implements RakuPointyBlock {
    public RakuPointyBlockImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public RakuParameter[] getParams() {
        RakuSignature sig = findChildByClass(RakuSignature.class);
        if (sig != null) return sig.getParameters();
        return new RakuParameter[]{};
    }

    @Override
    public void contributeScopeSymbols(RakuSymbolCollector collector) {
        collector.offerSymbol(new RakuImplicitSymbol(RakuSymbolKind.Variable, "&?BLOCK", this));
    }

    @Nullable
    @Override
    public PsiElement getTopic() {
        return findChildByClass(RakuSignature.class);
    }

    @Override
    public String getLambda() {
        ASTNode lambda = getNode().findChildByType(RakuTokenTypes.LAMBDA);
        return lambda == null ? "->" : lambda.getText();
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof RakuHighlightVisitor) {
            ((RakuHighlightVisitor)visitor).visitRakuElement(this);
        } else {
            super.accept(visitor);
        }
    }
}
