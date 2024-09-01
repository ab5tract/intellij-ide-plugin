package edument.rakuidea.psi.stub.impl;

import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.parsing.RakuElementTypes;
import edument.rakuidea.psi.RakuEnum;
import edument.rakuidea.psi.stub.RakuEnumStub;
import edument.rakuidea.psi.stub.RakuScopedDeclStub;

import java.util.Collection;

public class RakuEnumStubImpl extends StubBase<RakuEnum> implements RakuEnumStub {
    private final String enumName;
    private final boolean isExported;
    private final Collection<String> myEnumValues;

    public RakuEnumStubImpl(StubElement stub, String name, boolean exported, Collection<String> enumValues) {
        super(stub, RakuElementTypes.ENUM);
        enumName = name;
        isExported = exported;
        myEnumValues = enumValues;
    }

    @Override
    public String getTypeName() {
        return enumName;
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

    @Override
    public Collection<String> getEnumValues() {
        return myEnumValues;
    }
}
