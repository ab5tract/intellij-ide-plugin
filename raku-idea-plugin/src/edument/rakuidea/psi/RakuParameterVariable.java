package edument.rakuidea.psi;

import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.meta.PsiMetaOwner;
import edument.rakuidea.psi.symbols.RakuLexicalSymbolContributor;

public interface RakuParameterVariable extends RakuPsiDeclaration, PsiNamedElement, PsiMetaOwner,
                                               RakuLexicalSymbolContributor {
    String summary(boolean includeName);
}
