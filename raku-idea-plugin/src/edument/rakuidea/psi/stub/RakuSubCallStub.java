package edument.rakuidea.psi.stub;

import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.psi.RakuSubCall;

import java.util.Map;

public interface RakuSubCallStub extends StubElement<RakuSubCall> {
    String getName();
    Map<String, String> getAllFrameworkData();
    String getFrameworkData(String frameworkName, String key);
}
