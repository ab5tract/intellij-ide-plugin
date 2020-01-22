package edument.perl6idea.cro.template.psi;

import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import edument.perl6idea.cro.template.psi.stub.CroTemplateMacroStub;

public interface CroTemplateMacro extends StubBasedPsiElement<CroTemplateMacroStub>, NavigatablePsiElement, PsiNamedElement, Scope, Declaration {
}
