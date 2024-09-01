package edument.rakuidea.highlighter;

import com.intellij.psi.PsiElementVisitor;
import edument.rakuidea.psi.*;

public abstract class RakuElementVisitor extends PsiElementVisitor {
    public void visitRakuElement(RakuPsiElement element) {
        if (element instanceof RakuPsiScope) {
            visitScope((RakuPsiScope)element);
        }
    }

    public abstract void visitScope(RakuPsiScope element);
}
