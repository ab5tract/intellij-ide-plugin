package edument.rakuidea.psi.stub;

import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.psi.RakuNeedStatement;

import java.util.List;

public interface RakuNeedStatementStub extends StubElement<RakuNeedStatement> {
    List<String> getModuleNames();
}
