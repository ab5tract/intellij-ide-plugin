package edument.rakuidea.debugger;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NullableLazyValue;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.ui.ColoredTextContainer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.frame.XCompositeNode;
import com.intellij.xdebugger.frame.XStackFrame;
import com.intellij.xdebugger.frame.XValueChildrenList;
import com.intellij.xdebugger.impl.XSourcePositionImpl;
import edument.rakuidea.psi.RakuFile;
import edument.rakuidea.psi.stub.index.ProjectModulesStubIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;

public class RakuStackFrame extends XStackFrame {
    private final RakuStackFrameDescriptor myFrameDescriptor;
    private final RakuDebugThread myDebugThread;
    private final NullableLazyValue<VirtualFile> myVirtualFile;

    public RakuStackFrame(Project project, RakuStackFrameDescriptor frame, RakuExecutionStack stack) {
        myFrameDescriptor = frame;
        myDebugThread = stack.getSuspendContext().getDebugThread();
        myVirtualFile = NullableLazyValue.lazyNullable(() -> {
            String localFilePath = myFrameDescriptor.getFile().getPath();
            final VirtualFile[] file = {null};
            if (localFilePath.startsWith("site#")) {
                String moduleName = myFrameDescriptor.getFile().getModuleName();
                if (moduleName != null)
                    ApplicationManager.getApplication().runReadAction(() -> {
                        Collection<RakuFile> indexedFile = ProjectModulesStubIndex
                            .getInstance().get(moduleName, project, GlobalSearchScope.allScope(project));
                        if (!indexedFile.isEmpty())
                            file[0] = indexedFile.iterator().next().getVirtualFile();
                    });
            }
            if (file[0] != null)
                return file[0];
            return VfsUtil.findFileByIoFile(new File(localFilePath), true);
        });
    }

    @Override
    public void customizePresentation(@NotNull ColoredTextContainer component) {
        component.append(myFrameDescriptor.getPresentableName(), SimpleTextAttributes.REGULAR_ATTRIBUTES);
        component.setIcon(AllIcons.Debugger.Frame);
    }

    @Nullable
    @Override
    public XSourcePosition getSourcePosition() {
        VirtualFile file = myVirtualFile.getValue();
        if (file != null)
            return XSourcePositionImpl.create(file, myFrameDescriptor.getLine() - 1);
        return null;
    }

    @Override
    public void computeChildren(@NotNull XCompositeNode node) {
        RakuValueDescriptor[] lexicals = myFrameDescriptor.getLexicals();
        XValueChildrenList list = new XValueChildrenList();

        if (lexicals != null && lexicals.length > 0) {
            list.addTopGroup(new RakuXLexicalGroup("Lexical variables", lexicals, this));
            node.addChildren(list, true);
        } else {
            super.computeChildren(node);
        }
    }

    public RakuDebugThread getDebugThread() {
        return myDebugThread;
    }
}
