package edument.rakuidea.psi.stub;

import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.psi.RakuTrait;

public interface RakuTraitStub extends StubElement<RakuTrait> {
    String getTraitModifier();
    String getTraitName();
}
