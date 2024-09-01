package edument.rakuidea.psi.stub.impl;

import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.parsing.RakuElementTypes;
import edument.rakuidea.psi.RakuPackageDecl;
import edument.rakuidea.psi.stub.RakuPackageDeclStub;
import edument.rakuidea.psi.stub.RakuScopedDeclStub;

public class RakuPackageDeclStubImpl extends StubBase<RakuPackageDecl> implements RakuPackageDeclStub {
    private final String packageKind;
    private final String packageName;
    private final boolean isExported;

    public RakuPackageDeclStubImpl(StubElement parent, String packageKind, String packageName, boolean exported) {
        super(parent, RakuElementTypes.PACKAGE_DECLARATION);
        this.packageKind = packageKind;
        this.packageName = packageName;
        isExported = exported;
    }

    @Override
    public String getPackageKind() {
        return packageKind;
    }

    @Override
    public String getTypeName() {
        return packageName;
    }

    @Override
    public String getScope() {
        return getParentStub() instanceof RakuScopedDeclStub
               ? ((RakuScopedDeclStub)getParentStub()).getScope()
               : "our";
    }

    @Override
    public boolean isExported() {
        return isExported;
    }
}
