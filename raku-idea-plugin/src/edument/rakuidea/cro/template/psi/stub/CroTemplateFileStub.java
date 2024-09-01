package edument.rakuidea.cro.template.psi.stub;

import com.intellij.psi.stubs.PsiFileStub;
import edument.rakuidea.cro.template.psi.CroTemplateFile;
import edument.rakuidea.cro.template.psi.reference.CroTemplateSymbolCollector;

public interface CroTemplateFileStub extends PsiFileStub<CroTemplateFile> {
    void declareExportedSymbols(CroTemplateSymbolCollector collector);
}
