package edument.rakuidea.psi;

import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuSubCallStub;

public interface RakuSubCall extends RakuPsiElement, RakuCodeBlockCall, RakuExtractable,
                                     StubBasedPsiElement<RakuSubCallStub> {
    boolean maybeCoercion();
}
