package edument.perl6idea.annotation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.util.PsiTreeUtil;
import edument.perl6idea.annotation.fix.AddEvalPragmaFix;
import edument.perl6idea.psi.*;
import org.jetbrains.annotations.NotNull;

public class InterpolatedEVALAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!(element instanceof Perl6SubCallName)) return;
        if (!element.getText().equals("EVAL")) return;

        PsiElement arg = element.getNextSibling();
        if (arg == null) return;
        while (arg instanceof PsiWhiteSpace)
            arg = arg.getNextSibling();
        if (arg == null) return;

        Perl6PsiScope scope = PsiTreeUtil.getParentOfType(arg, Perl6PsiScope.class);
        while (scope != null) {
            Perl6StatementList list = PsiTreeUtil.findChildOfType(scope, Perl6StatementList.class);
            if (list == null) break;
            Perl6Statement[] stats = PsiTreeUtil.getChildrenOfType(list, Perl6Statement.class);
            if (stats == null) stats = new Perl6Statement[0];
            for (Perl6Statement statement : stats) {
                if (statement.getTextOffset() > arg.getTextOffset()) break;
                for (PsiElement child : statement.getChildren()) {
                    if (!(child instanceof Perl6UseStatement)) continue;
                    String moduleName = ((Perl6UseStatement)child).getModuleName();
                    if (moduleName == null || moduleName.equals("MONKEY") ||
                        moduleName.equals("MONKEY-SEE-NO-EVAL"))
                        return;
                }
            }
            scope = PsiTreeUtil.getParentOfType(scope, Perl6PsiScope.class);
        }

        if (arg instanceof Perl6StrLiteral) {
            String t = arg.getText(); // Literal text
            if (t.startsWith("Q")) return;
            if (t.startsWith("q") && !t.startsWith("qq")) return;
            // Check is variable used
            if (PsiTreeUtil.findChildOfType(arg, Perl6Variable.class) == null) return;
        }
        holder.newAnnotation(HighlightSeverity.ERROR, "Cannot EVAL interpolated expression without MONKEY-SEE-NO-EVAL pragma")
            .range(arg).withFix(new AddEvalPragmaFix()).create();
    }
}
