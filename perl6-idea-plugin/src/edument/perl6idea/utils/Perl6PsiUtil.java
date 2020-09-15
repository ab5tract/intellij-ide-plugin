package edument.perl6idea.utils;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import edument.perl6idea.psi.Perl6Comment;
import edument.perl6idea.psi.Perl6ElementFactory;
import edument.perl6idea.psi.Perl6Statement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static edument.perl6idea.parsing.Perl6TokenTypes.UNV_WHITE_SPACE;

public class Perl6PsiUtil {
    /**
     * If node element is a white space one, get next or prev non-whitespace sibling
     * @param node starting node to move from
     * @param toRight direction of move, next sibling if true, prev otherwise
     * @return non-whitespace PsiElement or null
     */
    @Nullable
    public static PsiElement skipSpaces(PsiElement node, boolean toRight) {
        PsiElement temp = node;
        while (temp != null && (temp instanceof PsiWhiteSpace || temp.getNode().getElementType().equals(UNV_WHITE_SPACE) || temp instanceof Perl6Comment))
            temp = toRight ? temp.getNextSibling() : temp.getPrevSibling();
        return temp;
    }

    @Nullable
    public static PsiElement getLastNonSpaceChild(@NotNull PsiElement node) {
        PsiElement[] children = node.getChildren();
        if (children.length == 0)
            return null;
        return skipSpaces(children[children.length - 1], false);
    }

    public static void terminateStatement(PsiElement element) {
        if (!(element instanceof Perl6Statement))
            return;

        Perl6Statement statement = Perl6ElementFactory.createStatementFromText(element.getProject(), element.getText() + ";");
        element.replace(statement);
    }
}
