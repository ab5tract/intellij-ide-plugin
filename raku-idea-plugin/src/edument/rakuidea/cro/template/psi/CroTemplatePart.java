package edument.rakuidea.cro.template.psi;

import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.cro.template.psi.stub.CroTemplatePartStub;

public interface CroTemplatePart extends StubBasedPsiElement<CroTemplatePartStub>, PsiNamedElement, Scope {
}
