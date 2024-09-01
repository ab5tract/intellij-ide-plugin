package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import edument.rakuidea.parsing.RakuTokenTypes;
import edument.rakuidea.psi.*;
import edument.rakuidea.psi.stub.RakuScopedDeclStub;
import org.jetbrains.annotations.NotNull;

public class RakuScopedDeclImpl extends StubBasedPsiElementBase<RakuScopedDeclStub> implements RakuScopedDecl {
    public RakuScopedDeclImpl(@NotNull ASTNode node) {
        super(node);
    }

    public RakuScopedDeclImpl(final RakuScopedDeclStub stub, final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    @Override
    public String getScope() {
        RakuScopedDeclStub stub = this.getStub();
        if (stub != null)
            return stub.getScope();

        ASTNode declarator = getNode().findChildByType(RakuTokenTypes.SCOPE_DECLARATOR);
        return declarator != null ? declarator.getText() : "";
    }

    public String toString() {
        return getClass().getSimpleName() + "(Perl6:SCOPED_DECLARATION)";
    }
}
