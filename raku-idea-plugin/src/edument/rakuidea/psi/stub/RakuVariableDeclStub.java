package edument.rakuidea.psi.stub;

import edument.rakuidea.psi.RakuVariableDecl;

public interface RakuVariableDeclStub extends RakuDeclStub<RakuVariableDecl> {
    String[] getVariableNames();
    String getVariableType();
}
