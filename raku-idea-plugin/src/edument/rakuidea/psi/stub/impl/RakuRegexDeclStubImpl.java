package edument.rakuidea.psi.stub.impl;

import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.parsing.RakuElementTypes;
import edument.rakuidea.psi.RakuRegexDecl;
import edument.rakuidea.psi.stub.RakuRegexDeclStub;
import edument.rakuidea.psi.stub.RakuScopedDeclStub;

public class RakuRegexDeclStubImpl extends StubBase<RakuRegexDecl> implements RakuRegexDeclStub {
    private final String regexName;
    private final boolean isExported;

    public RakuRegexDeclStubImpl(StubElement stub, String name, boolean exported) {
        super(stub, RakuElementTypes.REGEX_DECLARATION);
        this.regexName = name;
        isExported = exported;
    }

    @Override
    public String getRegexName() {
        return regexName;
    }

    @Override
    public String getScope() {
        return getParentStub() instanceof RakuScopedDeclStub
               ? ((RakuScopedDeclStub)getParentStub()).getScope()
               : "has";
    }

    @Override
    public boolean isExported() {
        return isExported;
    }
}
