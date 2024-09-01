package edument.rakuidea.annotation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuRoutineDecl;
import edument.rakuidea.psi.RakuSubCall;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.sdk.RakuSdkType;
import edument.rakuidea.sdk.RakuSettingTypeId;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class NonNillReturnAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof RakuSubCall call))
            return;

        if (!Objects.equals(call.getCallName(), "return"))
            return;

        if (call.getCallArguments().length == 0)
            return;

        RakuRoutineDecl routineDecl = PsiTreeUtil.getParentOfType(call, RakuRoutineDecl.class);
        if (routineDecl == null)
            return;

        RakuType retType = routineDecl.getReturnType();
        RakuType nilType = RakuSdkType.getInstance().getCoreSettingType(routineDecl.getProject(), RakuSettingTypeId.Nil);
        if (!Objects.equals(nilType, retType))
            return;

        holder.newAnnotation(HighlightSeverity.ERROR, "A value is returned from subroutine returning Nil")
            .range(call).create();
    }
}
