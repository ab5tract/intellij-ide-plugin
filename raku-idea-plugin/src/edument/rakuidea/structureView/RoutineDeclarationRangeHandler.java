package edument.rakuidea.structureView;

import com.intellij.codeInsight.hint.DeclarationRangeHandler;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuBlockoid;
import edument.rakuidea.psi.RakuRoutineDecl;
import org.jetbrains.annotations.NotNull;

@InternalIgnoreDependencyViolation
public class RoutineDeclarationRangeHandler implements DeclarationRangeHandler<RakuRoutineDecl> {
    @NotNull
    @Override
    public TextRange getDeclarationRange(@NotNull RakuRoutineDecl container) {
        RakuBlockoid blockoid = PsiTreeUtil.getChildOfType(container, RakuBlockoid.class);
        return blockoid != null
               ? new TextRange(container.getTextOffset(), blockoid.getTextOffset())
               : container.getTextRange();
    }
}
