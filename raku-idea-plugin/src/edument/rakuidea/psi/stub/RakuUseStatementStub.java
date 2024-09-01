package edument.rakuidea.psi.stub;

import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.psi.RakuUseStatement;

public interface RakuUseStatementStub extends StubElement<RakuUseStatement> {
    String getModuleName();
}
