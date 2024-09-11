package org.raku.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import org.raku.psi.RakuFile;
import org.raku.psi.RakuModuleName;
import org.raku.psi.RakuNeedStatement;
import org.raku.psi.stub.RakuNeedStatementStub;
import org.raku.psi.stub.RakuNeedStatementStubElementType;
import org.raku.psi.stub.index.ProjectModulesStubIndex;
import org.raku.psi.symbols.*;
import org.raku.sdk.RakuSdkType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class RakuNeedStatementImpl extends StubBasedPsiElementBase<RakuNeedStatementStub> implements RakuNeedStatement {
    public RakuNeedStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    public RakuNeedStatementImpl(RakuNeedStatementStub stub, RakuNeedStatementStubElementType type) {
        super(stub, type);
    }

    @Override
    public void contributeLexicalSymbols(RakuSymbolCollector collector) {
        // We cannot contribute based on stubs when indexing is in progress
        if (DumbService.isDumb(getProject())) return;
        for (String name : getModuleNames()) {
            Project project = getProject();
            Collection<RakuFile> found = ProjectModulesStubIndex.getInstance()
                    .get(name, project, GlobalSearchScope.projectScope(project));
            if (found.size() > 0) {
                RakuFile file = found.iterator().next();
                Set<String> seen = new HashSet<>();
                seen.add(name);
                file.contributeGlobals(collector, seen);
            }
            else {
                RakuFile file = RakuSdkType.getInstance().getPsiFileForModule(project, name, getText());
                if (file != null) {
                    file.contributeGlobals(collector, new HashSet<>());
                }
            }
        }
    }

    @Override
    public List<String> getModuleNames() {
        RakuNeedStatementStub stub = getStub();
        if (stub != null)
            return stub.getModuleNames();

        List<String> result = new ArrayList<>();
        for (RakuModuleName moduleName : findChildrenByClass(RakuModuleName.class))
            result.add(moduleName.getText());
        return result;
    }

    public String toString() {
        return getClass().getSimpleName() + "(Raku:NEED_STATEMENT)";
    }
}
