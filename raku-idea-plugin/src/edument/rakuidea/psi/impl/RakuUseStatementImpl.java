package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StubIndex;
import com.intellij.util.SlowOperations;
import edument.rakuidea.psi.RakuFile;
import edument.rakuidea.psi.RakuModuleName;
import edument.rakuidea.psi.RakuUseStatement;
import edument.rakuidea.psi.stub.RakuUseStatementStub;
import edument.rakuidea.psi.stub.RakuUseStatementStubElementType;
import edument.rakuidea.psi.stub.index.RakuStubIndexKeys;
import edument.rakuidea.psi.stub.index.ProjectModulesStubIndex;
import edument.rakuidea.psi.symbols.RakuSymbolCollector;
import edument.rakuidea.sdk.RakuSdkType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RakuUseStatementImpl extends StubBasedPsiElementBase<RakuUseStatementStub> implements RakuUseStatement {
    public RakuUseStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    public RakuUseStatementImpl(RakuUseStatementStub stub, RakuUseStatementStubElementType type) {
        super(stub, type);
    }

    @Override
    public void contributeLexicalSymbols(RakuSymbolCollector collector) {
        String name = getModuleName();
        if (name != null) {
            Project project = getProject();

            // We cannot contribute based on stubs when indexing is in progress
            if (DumbService.isDumb(getProject())) return;

            SlowOperations.allowSlowOperations(() -> {
                Collection<RakuFile> found =
                    ProjectModulesStubIndex.getInstance().get(name, project, GlobalSearchScope.projectScope(project));
                if (found.size() > 0) {
                    RakuFile file = found.iterator().next();
                    file.contributeGlobals(collector, new HashSet<>());
                    Set<String> seen = new HashSet<>();
                    seen.add(name);
                    file.contributeGlobals(collector, seen);
                }
                else {
                    Collection<RakuFile> elements =
                        StubIndex.getElements(RakuStubIndexKeys.PROJECT_MODULES, name, project, GlobalSearchScope.allScope(project),
                                              RakuFile.class);
                    if (!elements.isEmpty()) {
                        elements.iterator().next().contributeGlobals(collector, new HashSet<>());
                    }

                    if (collector.isSatisfied())
                        return;

                    RakuFile file = RakuSdkType.getInstance().getPsiFileForModule(project, name, getText());
                    if (file != null) {
                        file.contributeGlobals(collector, new HashSet<>());
                    }
                }
            });
        }
    }

    @Nullable
    @Override
    public String getModuleName() {
        RakuUseStatementStub stub = getStub();
        if (stub != null)
            return stub.getModuleName();

        RakuModuleName moduleName = findChildByClass(RakuModuleName.class);
        return moduleName != null ? moduleName.getText() : null;
    }

    public String toString() {
        return getClass().getSimpleName() + "(Raku:USE_STATEMENT)";
    }
}
