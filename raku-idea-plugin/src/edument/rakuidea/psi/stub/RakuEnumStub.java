package edument.rakuidea.psi.stub;

import edument.rakuidea.psi.RakuEnum;

import java.util.Collection;

public interface RakuEnumStub extends RakuTypeStub<RakuEnum> {
    Collection<String> getEnumValues();
}
