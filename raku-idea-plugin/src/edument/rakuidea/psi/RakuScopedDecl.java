package edument.rakuidea.psi;

import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuScopedDeclStub;

public interface RakuScopedDecl extends RakuPsiElement, StubBasedPsiElement<RakuScopedDeclStub> {
    String getScope();
}
