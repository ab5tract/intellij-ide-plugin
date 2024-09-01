package edument.rakuidea.psi;

import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuUseStatementStub;
import edument.rakuidea.psi.symbols.RakuLexicalSymbolContributor;
import org.jetbrains.annotations.Nullable;

public interface RakuUseStatement extends RakuLexicalSymbolContributor, StubBasedPsiElement<RakuUseStatementStub> {
    @Nullable
    String getModuleName();
}
