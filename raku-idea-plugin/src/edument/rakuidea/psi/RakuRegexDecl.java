package edument.rakuidea.psi;

import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuRegexDeclStub;
import edument.rakuidea.psi.symbols.RakuLexicalSymbolContributor;
import edument.rakuidea.psi.symbols.RakuMOPSymbolContributor;

public interface RakuRegexDecl extends RakuPsiScope, RakuPsiDeclaration,
                                       StubBasedPsiElement<RakuRegexDeclStub>,
                                       RakuSignatureHolder, RakuLexicalSymbolContributor,
                                       RakuMOPSymbolContributor {
    String getRegexKind();
    String getRegexName();
    @Override
    String getMultiness();
}
