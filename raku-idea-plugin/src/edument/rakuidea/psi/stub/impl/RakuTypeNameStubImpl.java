package edument.rakuidea.psi.stub.impl;

import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.parsing.RakuElementTypes;
import edument.rakuidea.psi.RakuTypeName;
import edument.rakuidea.psi.stub.RakuTypeNameStub;

public class RakuTypeNameStubImpl extends StubBase<RakuTypeName> implements RakuTypeNameStub {
    private final String typeName;

    public RakuTypeNameStubImpl(StubElement parent, String name) {
        super(parent, RakuElementTypes.TYPE_NAME);
        this.typeName = name;
    }


    @Override
    public String getTypeName() {
        return typeName;
    }
}
