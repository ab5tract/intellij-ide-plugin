package edument.perl6idea.editor.smartEnter;

import com.intellij.codeInsight.editorActions.smartEnter.SmartEnterProcessor;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import edument.perl6idea.psi.*;
import org.jetbrains.annotations.NotNull;

import static edument.perl6idea.parsing.Perl6TokenTypes.*;

public class Perl6SmartEnterProcessor extends SmartEnterProcessor {
    @Override
    public boolean process(@NotNull Project project, @NotNull Editor editor, @NotNull PsiFile psiFile) {
        if (!(psiFile instanceof Perl6File)) return false;
        final CaretModel caretModel = editor.getCaretModel();
        PsiElement element = psiFile.findElementAt(caretModel.getOffset() - 1);
        if (element.getNode().getElementType() == BAD_CHARACTER) return false;
        element = PsiTreeUtil.getParentOfType(element, Perl6Statement.class);
        if (element == null) return false;
        return processEnter(element, editor);
    }

    private static boolean processEnter(PsiElement element, Editor editor) {
        PsiElement child = element.getFirstChild();
        if (child == null) return false;
        if (child instanceof Perl6PackageDecl) {
            return processPackageDeclaration(child, editor);
        } else if (element.getLastChild().getNode().getElementType() == STATEMENT_TERMINATOR) {
            return false;
        } else {
            // Default handler for statements
            int offsetToJump = element.getTextOffset() + element.getTextLength();
            editor.getDocument().insertString(offsetToJump, ";\n");
            editor.getCaretModel().moveToOffset(offsetToJump + 2);
            return true;
        }
    }

    private static boolean processPackageDeclaration(PsiElement element, Editor editor) {
        PsiElement lastPiece = element.getLastChild();
        // Depend on last piece, we know what we want complete
        if (lastPiece.getNode().getElementType() == UNV_WHITE_SPACE) {
            if (PsiTreeUtil.getChildrenOfType(element, Perl6Blockoid.class) == null) {
                // Delete whitespace
                int offsetToJump = lastPiece.getTextOffset();
                lastPiece.delete();
                editor.getDocument().insertString(offsetToJump, " {\n\n}\n");
                editor.getCaretModel().moveToOffset(offsetToJump + 3);
            }
        } else if (lastPiece instanceof Perl6Trait ||
                   lastPiece instanceof Perl6RoleSignature ||
                   lastPiece.getNode().getElementType() == NAME) {
            int offsetToJump = lastPiece.getTextOffset() + lastPiece.getTextLength();
            editor.getDocument().insertString(offsetToJump, " {\n\n}\n");
            editor.getCaretModel().moveToOffset(offsetToJump + 3);
        } else if (lastPiece instanceof Perl6Blockoid) {
            boolean isNotClosed = lastPiece.getLastChild() instanceof Perl6StatementList;
            int offsetToJump = lastPiece.getTextOffset() + 1;
            editor.getDocument().insertString(offsetToJump,
                                              isNotClosed ? "\n\n}\n" : "\n\n");
            editor.getCaretModel().moveToOffset(offsetToJump + 1);
        } else {
            return false;
        }
        return true;
    }
}
