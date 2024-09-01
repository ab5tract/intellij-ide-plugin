package edument.rakuidea.psi;

import com.intellij.psi.StubBasedPsiElement;
import edument.rakuidea.psi.stub.RakuSubsetStub;
import edument.rakuidea.psi.stub.index.RakuIndexableType;

public interface RakuSubset extends RakuPsiDeclaration, StubBasedPsiElement<RakuSubsetStub>,
                                    RakuIndexableType {
    String getSubsetName();
    RakuPackageDecl getSubsetBaseType();
    String getSubsetBaseTypeName();
}
