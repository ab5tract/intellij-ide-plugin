package edument.rakuidea.cro.template.parsing;

import com.intellij.psi.stubs.*;
import edument.rakuidea.cro.template.CroTemplateLanguage;
import edument.rakuidea.cro.template.psi.CroTemplatePart;
import edument.rakuidea.cro.template.psi.impl.CroTemplatePartImpl;
import edument.rakuidea.cro.template.psi.stub.CroTemplatePartStub;
import edument.rakuidea.cro.template.psi.stub.impl.CroTemplatePartStubImpl;
import edument.rakuidea.cro.template.psi.stub.index.CroTemplateStubIndexKeys;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CroTemplatePartStubElementType extends IStubElementType<CroTemplatePartStub, CroTemplatePart> {
    public CroTemplatePartStubElementType() {
        super("PART", CroTemplateLanguage.INSTANCE);
    }

    @Override
    public CroTemplatePart createPsi(@NotNull CroTemplatePartStub stub) {
        return new CroTemplatePartImpl(stub, this);
    }

    @Override
    public @NotNull CroTemplatePartStub createStub(@NotNull CroTemplatePart psi, StubElement<?> parentStub) {
        return new CroTemplatePartStubImpl(parentStub, psi.getName());
    }

    @Override
    public @NotNull String getExternalId() {
        return "croTemplate.stub.part";
    }

    @Override
    public void serialize(@NotNull CroTemplatePartStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeUTF(stub.getName());
    }

    @Override
    public @NotNull CroTemplatePartStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new CroTemplatePartStubImpl(parentStub, dataStream.readUTF());
    }

    @Override
    public void indexStub(@NotNull CroTemplatePartStub stub, @NotNull IndexSink sink) {
        sink.occurrence(CroTemplateStubIndexKeys.TEMPLATE_PART, stub.getName());
    }
}
