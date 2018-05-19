package edument.perl6idea.psi;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.tree.IStubFileElementType;

import java.util.List;

public interface Perl6File extends Perl6PsiElement, Perl6PsiScope, PsiNameIdentifierOwner, PsiFile {
    List<Perl6PsiDeclaration> getExports();
}
