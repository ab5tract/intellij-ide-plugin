package edument.rakuidea.psi;

import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuConstantStub;
import edument.rakuidea.psi.symbols.RakuLexicalSymbolContributor;
import org.jetbrains.annotations.Nullable;

public interface RakuConstant extends RakuPsiDeclaration, StubBasedPsiElement<RakuConstantStub>,
                                      RakuLexicalSymbolContributor {
    @Nullable
    String getConstantName();
}
