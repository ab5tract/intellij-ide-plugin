package edument.rakuidea.structureView;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import edument.rakuidea.psi.RakuFile;
import edument.rakuidea.psi.RakuPackageDecl;
import edument.rakuidea.psi.RakuPsiElement;
import edument.rakuidea.psi.RakuSubCall;
import edument.rakuidea.psi.impl.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RakuStructureViewBuilder extends TreeBasedStructureViewBuilder {
    private final PsiFile psiFile;

    public RakuStructureViewBuilder(PsiFile psiFile) {
        this.psiFile = psiFile;
    }

    @NotNull
    @Override
    public StructureViewModel createStructureViewModel(@Nullable Editor editor) {
        return new StructureViewModelBase(psiFile, editor,
                    new RakuStructureViewElement((RakuPsiElement)psiFile))
                .withSorters(Sorter.ALPHA_SORTER)
                .withSuitableClasses(RakuFile.class, RakuPackageDecl.class,
                                     RakuRegexDeclImpl.class, RakuRoutineDeclImpl.class,
                                     RakuConstantImpl.class, RakuVariableDeclImpl.class,
                                     RakuSubsetImpl.class, RakuEnumImpl.class,
                                     RakuSubCall.class);
    }
}
