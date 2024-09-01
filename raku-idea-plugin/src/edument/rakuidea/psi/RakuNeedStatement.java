package edument.rakuidea.psi;

import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuNeedStatementStub;
import edument.rakuidea.psi.symbols.RakuLexicalSymbolContributor;

import java.util.List;

public interface RakuNeedStatement extends RakuLexicalSymbolContributor, StubBasedPsiElement<RakuNeedStatementStub> {
    List<String> getModuleNames();
}
