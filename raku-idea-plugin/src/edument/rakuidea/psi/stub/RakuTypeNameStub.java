package edument.rakuidea.psi.stub;

import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.psi.RakuTypeName;

public interface RakuTypeNameStub extends StubElement<RakuTypeName> {
    String getTypeName();
}
