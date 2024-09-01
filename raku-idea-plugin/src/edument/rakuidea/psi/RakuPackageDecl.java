package edument.rakuidea.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuPackageDeclStub;
import edument.rakuidea.psi.stub.index.RakuIndexableType;
import edument.rakuidea.psi.symbols.RakuLexicalSymbolContributor;
import edument.rakuidea.psi.symbols.RakuMOPSymbolContributor;
import edument.rakuidea.psi.symbols.RakuSymbolCollector;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface RakuPackageDecl extends RakuPsiScope, RakuPsiDeclaration,
                                         StubBasedPsiElement<RakuPackageDeclStub>,
                                         RakuIndexableType, PsiNamedElement, RakuExtractable,
                                         RakuLexicalSymbolContributor, RakuMOPSymbolContributor {
    String getPackageKind();
    String getPackageName();
    boolean isStubbed();
    @Nullable
    PsiElement getPackageKeywordNode();
    void contributeNestedPackagesWithPrefix(RakuSymbolCollector collector, String prefix);
    List<RakuPackageDecl> collectChildren();
    List<RakuPackageDecl> collectParents();
    boolean trustsOthers();
    @Nullable
    RakuPackageDecl getMetaClass();
    void setMetaClass(RakuPackageDecl metaClass);
    RakuParameter[] getSignature();
}
