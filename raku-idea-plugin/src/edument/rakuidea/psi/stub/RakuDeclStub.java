package edument.rakuidea.psi.stub;

import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.psi.RakuPsiDeclaration;

public interface RakuDeclStub<T extends PsiElement & RakuPsiDeclaration> extends StubElement<T> {
    String getScope();
    boolean isExported();
}
