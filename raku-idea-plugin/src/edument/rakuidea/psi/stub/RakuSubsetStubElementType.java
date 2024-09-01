package edument.rakuidea.psi.stub;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.*;
import com.intellij.util.io.StringRef;
import edument.rakuidea.RakuLanguage;
import edument.rakuidea.psi.RakuSubset;
import edument.rakuidea.psi.impl.RakuSubsetImpl;
import edument.rakuidea.psi.stub.impl.RakuSubsetStubImpl;
import edument.rakuidea.psi.stub.index.RakuStubIndexKeys;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class RakuSubsetStubElementType extends IStubElementType<RakuSubsetStub, RakuSubset> {
    public RakuSubsetStubElementType() {
        super("SUBSET", RakuLanguage.INSTANCE);
    }

    @Override
    public RakuSubset createPsi(@NotNull RakuSubsetStub stub) {
        return new RakuSubsetImpl(stub, this);
    }

    @NotNull
    @Override
    public RakuSubsetStub createStub(@NotNull RakuSubset psi, StubElement parentStub) {
        return new RakuSubsetStubImpl(parentStub, psi.getSubsetName(), psi.isExported(), psi.getSubsetBaseTypeName());
    }

    @NotNull
    @Override
    public String getExternalId() {
        return "perl6.stub.subset";
    }

    @Override
    public void serialize(@NotNull RakuSubsetStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getTypeName());
        dataStream.writeBoolean(stub.isExported());
        dataStream.writeName(stub.getSubsetBaseTypeName());
    }

    @NotNull
    @Override
    public RakuSubsetStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        StringRef subsetNameRef = dataStream.readName();
        boolean exported = dataStream.readBoolean();
        StringRef subsetBaseRef = dataStream.readName();
        assert subsetNameRef != null;
        assert subsetBaseRef != null;
        return new RakuSubsetStubImpl(parentStub, subsetNameRef.getString(), exported, subsetBaseRef.getString());
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        String subsetName = ((RakuSubset) node.getPsi()).getSubsetName();
        return subsetName != null && !subsetName.equals("<anon>");
    }

    @Override
    public void indexStub(@NotNull RakuSubsetStub stub, @NotNull IndexSink sink) {
        String globalName = stub.getGlobalName();
        if (globalName != null) {
            sink.occurrence(RakuStubIndexKeys.GLOBAL_TYPES, globalName);
        }
        else {
            String lexicalName = stub.getLexicalName();
            if (lexicalName != null)
                sink.occurrence(RakuStubIndexKeys.LEXICAL_TYPES, lexicalName);
        }
    }
}
