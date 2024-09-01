package edument.rakuidea.refactoring.inline;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.usageView.UsageViewBundle;
import com.intellij.usageView.UsageViewDescriptor;
import edument.rakuidea.psi.RakuRoutineDecl;
import edument.rakuidea.psi.RakuVariableDecl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RakuInlineViewDescriptor implements UsageViewDescriptor {
  private final PsiElement myElement;

  public RakuInlineViewDescriptor(PsiElement routine) {
    myElement = routine;
  }


  @NotNull
  @Override
  public PsiElement[] getElements() {
    return new PsiElement[]{myElement};
  }

  @Override
  public String getProcessedElementsHeader() {
    if (myElement instanceof RakuRoutineDecl) {
      return ((RakuRoutineDecl)myElement).getRoutineKind() + " to inline";
    } else if (myElement instanceof RakuVariableDecl) {
      return String.join(", ", ((RakuVariableDecl)myElement).getVariableNames()) + " to inline";
    }
    return "Unknown element";
  }

  @NotNull
  @Override
  public String getCodeReferencesText(int usagesCount, int filesCount) {
    return RefactoringBundle.message("invocations.to.be.inlined", UsageViewBundle.getReferencesString(usagesCount, filesCount));
  }

  @Nullable
  @Override
  public String getCommentReferencesText(int usagesCount, int filesCount) {
    return RefactoringBundle.message("comments.elements.header",
                                     UsageViewBundle.getOccurencesString(usagesCount, filesCount));
  }
}
