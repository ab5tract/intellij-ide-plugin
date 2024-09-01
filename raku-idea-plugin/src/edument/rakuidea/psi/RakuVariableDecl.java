package edument.rakuidea.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuVariableDeclStub;
import edument.rakuidea.psi.symbols.RakuLexicalSymbolContributor;
import edument.rakuidea.psi.symbols.RakuMOPSymbolContributor;
import org.jetbrains.annotations.Nullable;

public interface RakuVariableDecl extends
                                  PsiNameIdentifierOwner, StubBasedPsiElement<RakuVariableDeclStub>,
                                  RakuLexicalSymbolContributor, RakuMOPSymbolContributor, RakuVariableSource {
    boolean hasInitializer();
    @Nullable
    PsiElement getInitializer(RakuVariable variable);
    @Nullable
    PsiElement getInitializer();
    void removeVariable(RakuVariable variable);
}
