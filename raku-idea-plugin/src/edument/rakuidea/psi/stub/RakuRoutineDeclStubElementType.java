package edument.rakuidea.psi.stub;

import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import edument.rakuidea.RakuLanguage;
import edument.rakuidea.psi.RakuRoutineDecl;
import edument.rakuidea.psi.impl.RakuRoutineDeclImpl;
import edument.rakuidea.psi.stub.impl.RakuRoutineDeclStubImpl;
import edument.rakuidea.psi.stub.index.RakuStubIndexKeys;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.psi.type.RakuUntyped;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class RakuRoutineDeclStubElementType extends IStubElementType<RakuRoutineDeclStub, RakuRoutineDecl> {
    public RakuRoutineDeclStubElementType() {
        super("ROUTINE_DECLARATION", RakuLanguage.INSTANCE);
    }

    @Override
    public RakuRoutineDecl createPsi(@NotNull RakuRoutineDeclStub stub) {
        return new RakuRoutineDeclImpl(stub, this);
    }

    @NotNull
    @Override
    public RakuRoutineDeclStub createStub(@NotNull RakuRoutineDecl psi, StubElement parentStub) {
        RakuType returnType = psi.getReturnType();
        String returnTypeName = returnType instanceof RakuUntyped ? "" : returnType.getName();
        return new RakuRoutineDeclStubImpl(parentStub, psi.getRoutineName(), psi.getRoutineKind(),
                                           psi.isPrivate(), psi.isExported(), psi.getMultiness(), returnTypeName);
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "raku.stub.routineDeclaration";
    }

    @Override
    public void serialize(@NotNull RakuRoutineDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getRoutineName());
        dataStream.writeName(stub.getRoutineKind());
        dataStream.writeBoolean(stub.isPrivate());
        dataStream.writeBoolean(stub.isExported());
        dataStream.writeName(stub.getMultiness());
        dataStream.writeName(stub.getReturnType());
    }

    @NotNull
    @Override
    public RakuRoutineDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        StringRef routineNameRef = dataStream.readName();
        StringRef routineKindRef = dataStream.readName();
        boolean isPrivate = dataStream.readBoolean();
        boolean exported = dataStream.readBoolean();
        StringRef multiness = dataStream.readName();
        StringRef returnType = dataStream.readName();
        assert routineNameRef != null && routineKindRef != null && multiness != null;
        return new RakuRoutineDeclStubImpl(parentStub, routineNameRef.getString(), routineKindRef.getString(), isPrivate,
                                           exported, multiness.getString(), returnType == null ? null : returnType.getString());
    }

    @Override
    public void indexStub(@NotNull RakuRoutineDeclStub stub, @NotNull IndexSink sink) {
        sink.occurrence(RakuStubIndexKeys.ALL_ROUTINES, stub.getRoutineName());
    }
}
