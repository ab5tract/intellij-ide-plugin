package edument.rakuidea.psi.stub;

import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.psi.RakuScopedDecl;

public interface RakuScopedDeclStub extends StubElement<RakuScopedDecl> {
    String getScope();
}
