package edument.rakuidea.editor;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import edument.rakuidea.highlighter.RakuHighlighter;
import edument.rakuidea.psi.RakuRegexSigspace;
import org.jetbrains.annotations.NotNull;

public class SigSpaceAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder annotationHolder) {
        if (psiElement instanceof RakuRegexSigspace && psiElement.getTextLength() >= 1) {
            annotationHolder.newAnnotation(HighlightSeverity.INFORMATION, "Implicit <.ws> call")
                .range(psiElement).textAttributes(RakuHighlighter.REGEX_SIG_SPACE).create();
        }
    }
}
