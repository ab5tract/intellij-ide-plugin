package edument.rakuidea.psi;

import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuTypeNameStub;

public interface RakuTypeName extends RakuPsiElement, StubBasedPsiElement<RakuTypeNameStub>,
                                      PsiNamedElement, RakuExtractable {
    String getTypeName();
}
