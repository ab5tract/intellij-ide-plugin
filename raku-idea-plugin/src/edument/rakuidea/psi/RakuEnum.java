package edument.rakuidea.psi;

import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuEnumStub;
import edument.rakuidea.psi.stub.index.RakuIndexableType;
import edument.rakuidea.psi.symbols.RakuLexicalSymbolContributor;

import java.util.Collection;

public interface RakuEnum extends RakuPsiDeclaration, StubBasedPsiElement<RakuEnumStub>,
                                  RakuIndexableType, RakuLexicalSymbolContributor {
    String getEnumName();
    Collection<String> getEnumValues();
}
