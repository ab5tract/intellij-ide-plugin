package edument.rakuidea.editor;

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import edument.rakuidea.parsing.RakuTokenTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalIgnoreDependencyViolation
public class RakuBraceMatcher implements PairedBraceMatcher {
    @Override
    public BracePair @NotNull [] getPairs() {
        return new BracePair[]{
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.BLOCK_CURLY_BRACKET_OPEN, edument.rakuidea.parsing.RakuTokenTypes.BLOCK_CURLY_BRACKET_CLOSE, true),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.STRING_LITERAL_QUOTE_OPEN, edument.rakuidea.parsing.RakuTokenTypes.STRING_LITERAL_QUOTE_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.PARENTHESES_OPEN, edument.rakuidea.parsing.RakuTokenTypes.PARENTHESES_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.CONTEXTUALIZER_OPEN, edument.rakuidea.parsing.RakuTokenTypes.CONTEXTUALIZER_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.SIGNATURE_BRACKET_OPEN, edument.rakuidea.parsing.RakuTokenTypes.SIGNATURE_BRACKET_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.TYPE_COERCION_PARENTHESES_OPEN, edument.rakuidea.parsing.RakuTokenTypes.TYPE_COERCION_PARENTHESES_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.REGEX_CAPTURE_PARENTHESES_OPEN, edument.rakuidea.parsing.RakuTokenTypes.REGEX_CAPTURE_PARENTHESES_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.ARRAY_INDEX_BRACKET_OPEN, edument.rakuidea.parsing.RakuTokenTypes.ARRAY_INDEX_BRACKET_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.HASH_INDEX_BRACKET_OPEN, edument.rakuidea.parsing.RakuTokenTypes.HASH_INDEX_BRACKET_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.ARRAY_COMPOSER_OPEN, edument.rakuidea.parsing.RakuTokenTypes.ARRAY_COMPOSER_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.REGEX_GROUP_BRACKET_OPEN, edument.rakuidea.parsing.RakuTokenTypes.REGEX_GROUP_BRACKET_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.REGEX_ASSERTION_ANGLE_OPEN, edument.rakuidea.parsing.RakuTokenTypes.REGEX_ASSERTION_ANGLE_CLOSE, false),
                new BracePair(edument.rakuidea.parsing.RakuTokenTypes.POD_FORMAT_STARTER, edument.rakuidea.parsing.RakuTokenTypes.POD_FORMAT_STOPPER, false),
                new BracePair(RakuTokenTypes.COMMENT_QUOTE_OPEN, edument.rakuidea.parsing.RakuTokenTypes.COMMENT_QUOTE_CLOSE, false),
        };
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType, @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
