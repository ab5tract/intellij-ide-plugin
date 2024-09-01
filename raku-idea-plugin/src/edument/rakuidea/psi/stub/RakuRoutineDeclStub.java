package edument.rakuidea.psi.stub;

import edument.rakuidea.psi.RakuRoutineDecl;

public interface RakuRoutineDeclStub extends RakuDeclStub<RakuRoutineDecl> {
    String getRoutineKind();
    String getRoutineName();
    boolean isPrivate();
    String getMultiness();
    String getReturnType();
}
