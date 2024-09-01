package edument.rakuidea.cro.template.psi;

import com.intellij.psi.PsiFile;
import edument.rakuidea.cro.template.psi.reference.CroTemplateSymbolCollector;

public interface CroTemplateFile extends PsiFile, Scope {
    void declareExportedSymbols(CroTemplateSymbolCollector collector);
}
