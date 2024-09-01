package edument.rakuidea.surrountWith.descriptors;

import com.intellij.lang.surroundWith.SurroundDescriptor;
import com.intellij.lang.surroundWith.Surrounder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.*;
import edument.rakuidea.psi.RakuStatement;
import edument.rakuidea.psi.RakuStatementList;
import edument.rakuidea.surrountWith.descriptors.surrounder.*;
import edument.rakuidea.utils.RakuPsiUtil;
import org.jetbrains.annotations.NotNull;

public class Perl6ExpressionSurroundDescriptor implements SurroundDescriptor {
    private static final Surrounder[] SURROUNDERS = {
        new Perl6IfSurrounder(false),
        new Perl6WithSurrounder(false),
        new Perl6UnlessSurrounder(false),
        new Perl6WithoutSurrounder(false),
        new Perl6GivenSurrounder(false),
        new Perl6ForSurrounder(false),
        new Perl6WheneverSurrounder(false),
        new Perl6WhenSurrounder(false),
        new Perl6TrySurrounder(false),
        new Perl6TryCatchWhenSurrounder(false),
        new Perl6TryCatchDefaultSurrounder(false),
        new Perl6StartSurrounder(false),
        new Perl6PointyBlockSurrounder(false),
        new Perl6HashSurrounder(false),
        new Perl6ArraySurrounder(false),
        new Perl6ArrayContextSurrounder(false),
        new Perl6HashContextSurrounder(false),
        new Perl6CornerBracketsSurrounder(false)
    };

    @Override
    public PsiElement @NotNull [] getElementsToSurround(PsiFile file, int startOffset, int endOffset) {
        PsiElement start = file.findElementAt(startOffset);
        PsiElement end = file.findElementAt(endOffset == 0 ? 0 : endOffset - 1);
        start = RakuPsiUtil.skipSpaces(start, true);
        end = RakuPsiUtil.skipSpaces(end, false);
        if (start == null || end == null)
            return PsiElement.EMPTY_ARRAY;

        PsiElement parent = PsiTreeUtil.findCommonParent(start, end);
        if (parent instanceof RakuRegex)
            return PsiElement.EMPTY_ARRAY;
        while (parent != null && !(parent instanceof RakuStatementList || parent instanceof RakuStatement || parent.getParent() instanceof RakuStatement)) {
            if (parent instanceof RakuExtractable)
                return new PsiElement[]{parent};
            parent = parent.getParent();
        }
        return PsiElement.EMPTY_ARRAY;
    }

    @Override
    public Surrounder @NotNull [] getSurrounders() {
        return SURROUNDERS;
    }

    @Override
    public boolean isExclusive() {
        return false;
    }
}
