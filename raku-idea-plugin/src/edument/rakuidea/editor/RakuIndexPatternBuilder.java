package edument.rakuidea.editor;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.search.IndexPatternBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import edument.rakuidea.cro.template.parsing.CroTemplateLexer;
import edument.rakuidea.cro.template.parsing.CroTemplateTokenTypes;
import edument.rakuidea.cro.template.psi.CroTemplateFile;
import edument.rakuidea.parsing.RakuLexer;
import edument.rakuidea.parsing.RakuTokenTypes;
import edument.rakuidea.psi.RakuFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalIgnoreDependencyViolation
public class RakuIndexPatternBuilder implements IndexPatternBuilder {
    public static final TokenSet RAKU_COMMENT_TOKEN_SET = TokenSet.create(RakuTokenTypes.COMMENT, CroTemplateTokenTypes.COMMENT);

    @Override
    public @Nullable Lexer getIndexingLexer(@NotNull PsiFile file) {
        if (file instanceof RakuFile) {
            return new RakuLexer();
        }
        else if (file instanceof CroTemplateFile) {
            return new CroTemplateLexer();
        }
        return null;
    }

    @Override
    public @Nullable TokenSet getCommentTokenSet(@NotNull PsiFile file) {
        if (file instanceof RakuFile || file instanceof CroTemplateFile) {
            return RAKU_COMMENT_TOKEN_SET;
        }
        return null;
    }

    @Override
    public int getCommentStartDelta(IElementType tokenType) {
        return 1;
    }

    @Override
    public int getCommentEndDelta(IElementType tokenType) {
        return tokenType == CroTemplateTokenTypes.COMMENT ? 3 : 0;
    }
}
