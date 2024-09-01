package edument.rakuidea.cro.template.psi;

import com.intellij.psi.PsiElement;
import edument.rakuidea.cro.template.psi.reference.CroTemplateSymbolCollector;

public interface Declaration extends PsiElement {
    void declareToCollector(CroTemplateSymbolCollector collector);
}
