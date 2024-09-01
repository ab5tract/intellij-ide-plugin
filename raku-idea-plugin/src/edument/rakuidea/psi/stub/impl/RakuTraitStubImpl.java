package edument.rakuidea.psi.stub.impl;

import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import edument.rakuidea.parsing.RakuElementTypes;
import edument.rakuidea.psi.RakuTrait;
import edument.rakuidea.psi.stub.RakuTraitStub;

public class RakuTraitStubImpl extends StubBase<RakuTrait> implements RakuTraitStub {
    private final String modifier;
    private final String name;

    public RakuTraitStubImpl(StubElement parent, String modifier, String name) {
        super(parent, RakuElementTypes.TRAIT);
        this.modifier = modifier;
        this.name = name;
    }

    @Override
    public String getTraitModifier() {
        return modifier;
    }

    @Override
    public String getTraitName() {
        return name;
    }
}
