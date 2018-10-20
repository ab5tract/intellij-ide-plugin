package edument.perl6idea.annotation;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import edument.perl6idea.annotation.fix.StubMissingPrivateMethodFix;
import edument.perl6idea.psi.Perl6MethodCall;
import edument.perl6idea.psi.Perl6RoutineDecl;
import edument.perl6idea.psi.Perl6Self;
import org.jetbrains.annotations.NotNull;

public class UndeclaredPrivateMethod implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof Perl6MethodCall))
            return;
        final Perl6MethodCall call = (Perl6MethodCall)element;
        String methodName = call.getCallName();
        PsiElement caller = call.getPrevSibling();

        // Annotate only private methods for now
        if (!methodName.startsWith("!")) return;

        PsiReference reference = call.getReference();
        if (reference == null) return;
        PsiElement declaration = reference.resolve();
        if (declaration != null) return;
        PsiElement prev = call.getPrevSibling();
        if (prev instanceof Perl6RoutineDecl) {
            holder.createErrorAnnotation(
                    element,
                    "Subroutine cannot start with '!'");
        } else {
            int offset = call.getTextOffset();
            Annotation annotation = holder.createErrorAnnotation(
                new TextRange(offset, offset + methodName.length()),
                String.format("Private method %s is used, but not declared", methodName));
            if (caller instanceof Perl6Self)
                annotation.registerFix(
                    new StubMissingPrivateMethodFix(methodName, call));
        }
    }
}
