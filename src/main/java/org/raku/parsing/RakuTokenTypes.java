package org.raku.parsing;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

public interface RakuTokenTypes {
    IElementType ALSO = new RakuElementType("ALSO");
    IElementType ARGLIST_EMPTY = new RakuElementType("ARGLIST_EMPTY");
    IElementType ARGLIST_END = new RakuElementType("ARGLIST_END");
    IElementType ARGLIST_START = new RakuElementType("ARGLIST_START");
    IElementType ARRAY_COMPOSER_CLOSE = new RakuElementType("ARRAY_COMPOSER_CLOSE");
    IElementType ARRAY_COMPOSER_OPEN = new RakuElementType("ARRAY_COMPOSER_OPEN");
    IElementType ARRAY_INDEX_BRACKET_CLOSE = new RakuElementType("ARRAY_INDEX_BRACKET_CLOSE");
    IElementType ARRAY_INDEX_BRACKET_OPEN = new RakuElementType("ARRAY_INDEX_BRACKET_OPEN");
    IElementType ASSIGN_METAOP = new RakuElementType("ASSIGN_METAOP");
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType BAD_ESCAPE = new RakuElementType("BAD_ESCAPE");
    IElementType BARE_BLOCK = new RakuElementType("BARE_BLOCK");
    IElementType BLOCK_CURLY_BRACKET_CLOSE = new RakuElementType("BLOCK_CURLY_BRACKET_CLOSE");
    IElementType BLOCK_CURLY_BRACKET_OPEN = new RakuElementType("BLOCK_CURLY_BRACKET_OPEN");
    IElementType BRACKETED_INFIX_INCOMPLETE = new RakuElementType("BRACKETED_INFIX_INCOMPLETE");
    IElementType CAPTURE_INVALID = new RakuElementType("CAPTURE_INVALID");
    IElementType CAPTURE_TERM = new RakuElementType("CAPTURE_TERM");
    IElementType CIRCUMFIX_CONTEXTUALIZER = new RakuElementType("CIRCUMFIX_CONTEXTUALIZER");
    IElementType COLON_PAIR = new RakuElementType("COLON_PAIR");
    IElementType COLON_PAIR_HAS_VALUE = new RakuElementType("COLON_PAIR_HAS_VALUE");
    IElementType COMMENT = new RakuElementType("COMMENT");
    IElementType COMMENT_QUOTE_CLOSE = new RakuElementType("COMMENT_QUOTE_CLOSE");
    IElementType COMMENT_QUOTE_OPEN = new RakuElementType("COMMENT_QUOTE_OPEN");
    IElementType COMMENT_STARTER = new RakuElementType("COMMENT_STARTER");
    IElementType COMPLEX_LITERAL = new RakuElementType("COMPLEX_LITERAL");
    IElementType COND_OP_INCOMPLETE = new RakuElementType("COND_OP_INCOMPLETE");
    IElementType CONSTANT_ANON = new RakuElementType("CONSTANT_ANON");
    IElementType CONSTANT_MISSING_INITIALIZER = new RakuElementType("CONSTANT_MISSING_INITIALIZER");
    IElementType CONTEXTUALIZER = new RakuElementType("CONTEXTUALIZER");
    IElementType CONTEXTUALIZER_CLOSE = new RakuElementType("CONTEXTUALIZER_CLOSE");
    IElementType CONTEXTUALIZER_OPEN = new RakuElementType("CONTEXTUALIZER_OPEN");
    IElementType DOTTY_NEXT_TERM = new RakuElementType("DOTTY_NEXT_TERM");
    IElementType EAT_TERMINATOR_HAS_HEREDOC = new RakuElementType("EAT_TERMINATOR_HAS_HEREDOC");
    IElementType EMPTY_STATEMENT = new RakuElementType("EMPTY_STATEMENT");
    IElementType END_OF_EXPR = new RakuElementType("END_OF_EXPR");
    IElementType END_OF_PARAMETERS = new RakuElementType("END_OF_PARAMETERS");
    IElementType END_OF_STATEMENT = new RakuElementType("END_OF_STATEMENT");
    IElementType END_OF_STATEMENT_MARK = new RakuElementType("END_OF_STATEMENT_MARK");
    IElementType END_OF_STATEMENT_STOPPER = new RakuElementType("END_OF_STATEMENT_STOPPER");
    IElementType ENUM_ANON = new RakuElementType("ENUM_ANON");
    IElementType ENUM_INCOMPLETE = new RakuElementType("ENUM_INCOMPLETE");
    IElementType ESCAPE_ARRAY = new RakuElementType("ESCAPE_ARRAY");
    IElementType ESCAPE_FUNCTION = new RakuElementType("ESCAPE_FUNCTION");
    IElementType ESCAPE_HASH = new RakuElementType("ESCAPE_HASH");
    IElementType ESCAPE_SCALAR = new RakuElementType("ESCAPE_SCALAR");
    IElementType FAKE_INFIX = new RakuElementType("FAKE_INFIX");
    IElementType FORMAT_CODE = new RakuElementType("FORMAT_CODE");
    IElementType HASH_INDEX_BRACKET_CLOSE = new RakuElementType("HASH_INDEX_BRACKET_CLOSE");
    IElementType HASH_INDEX_BRACKET_OPEN = new RakuElementType("HASH_INDEX_BRACKET_OPEN");
    IElementType HEREDOC = new RakuElementType("HEREDOC");
    IElementType HYPER_METAOP_MISSING = new RakuElementType("HYPER_METAOP_MISSING");
    IElementType HYPER_WHATEVER = new RakuElementType("HYPER_WHATEVER");
    IElementType INCOMPLETE_SCOPED_DECLARATION = new RakuElementType("INCOMPLETE_SCOPED_DECLARATION");
    IElementType INCOMPLETE_TYPE_NAME = new RakuElementType("INCOMPLETE_TYPE_NAME");
    IElementType INFIX = new RakuElementType("INFIX");
    IElementType INFIX_FUNCTION = new RakuElementType("INFIX_FUNCTION");
    IElementType INFIX_NOUN_VARIABLE = new RakuElementType("INFIX_NOUN_VARIABLE");
    IElementType INITIALIZER_MISSING = new RakuElementType("INITIALIZER_MISSING");
    IElementType INTEGER_LITERAL = new RakuElementType("INTEGER_LITERAL");
    IElementType INVOCANT_MARKER = new RakuElementType("INVOCANT_MARKER");
    IElementType IS_DOTTY = new RakuElementType("IS_DOTTY");
    IElementType IS_PARAM_TERM_QUANT = new RakuElementType("IS_PARAM_TERM_QUANT");
    IElementType LABEL_COLON = new RakuElementType("LABEL_COLON");
    IElementType LABEL_NAME = new RakuElementType("LABEL_NAME");
    IElementType LAMBDA = new RakuElementType("LAMBDA");
    IElementType LONGNAME_COLONPAIR = new RakuElementType("LONGNAME_COLONPAIR");
    IElementType METAOP = new RakuElementType("METAOP");
    IElementType METHOD_CALL_NAME = new RakuElementType("METHOD_CALL_NAME");
    IElementType METHOD_CALL_OPERATOR = new RakuElementType("METHOD_CALL_OPERATOR");
    IElementType MISSING_BLOCK = new RakuElementType("MISSING_BLOCK");
    IElementType MISSING_BLORST = new RakuElementType("MISSING_BLORST");
    IElementType MISSING_REGEX = new RakuElementType("MISSING_REGEX");
    IElementType MISSING_RETURN_CONSTRAINT = new RakuElementType("MISSING_RETURN_CONSTRAINT");
    IElementType MULTI_DECLARATOR = new RakuElementType("MULTI_DECLARATOR");
    IElementType NAME = new RakuElementType("NAME");
    IElementType NAMED_PARAMETER_NAME_ALIAS = new RakuElementType("NAMED_PARAMETER_NAME_ALIAS");
    IElementType NAMED_PARAMETER_SYNTAX = new RakuElementType("NAMED_PARAMETER_SYNTAX");
    IElementType NOT_DOTTY = new RakuElementType("NOT_DOTTY");
    IElementType NO_ARGS = new RakuElementType("NO_ARGS");
    IElementType NULL_TERM = new RakuElementType("NULL_TERM");
    IElementType NUMBER_LITERAL = new RakuElementType("NUMBER_LITERAL");
    IElementType ONLY_STAR = new RakuElementType("ONLY_STAR");
    IElementType PACKAGE_DECLARATOR = new RakuElementType("PACKAGE_DECLARATOR");
    IElementType PACKAGE_NAME = new RakuElementType("PACKAGE_NAME");
    IElementType PAIR_KEY = new RakuElementType("PAIR_KEY");
    IElementType PARAMETER_ANON = new RakuElementType("PARAMETER_ANON");
    IElementType PARAMETER_INCOMPLETE = new RakuElementType("PARAMETER_INCOMPLETE");
    IElementType PARAMETER_QUANTIFIER = new RakuElementType("PARAMETER_QUANTIFIER");
    IElementType PARAMETER_SEPARATOR = new RakuElementType("PARAMETER_SEPARATOR");
    IElementType PARAM_ARRAY_SHAPE = new RakuElementType("PARAM_ARRAY_SHAPE");
    IElementType PARENTHESES_CLOSE = new RakuElementType("PARENTHESES_CLOSE");
    IElementType PARENTHESES_OPEN = new RakuElementType("PARENTHESES_OPEN");
    IElementType PARSING_INITIALIZER = new RakuElementType("PARSING_INITIALIZER");
    IElementType PHASER = new RakuElementType("PHASER");
    IElementType POD_CODE = new RakuElementType("POD_CODE");
    IElementType POD_CONFIGURATION = new RakuElementType("POD_CONFIGURATION");
    IElementType POD_DIRECTIVE = new RakuElementType("POD_DIRECTIVE");
    IElementType POD_FINISH_TEXT = new RakuElementType("POD_FINISH_TEXT");
    IElementType POD_FORMAT_SEPARATOR = new RakuElementType("POD_FORMAT_SEPARATOR");
    IElementType POD_FORMAT_STARTER = new RakuElementType("POD_FORMAT_STARTER");
    IElementType POD_FORMAT_STOPPER = new RakuElementType("POD_FORMAT_STOPPER");
    IElementType POD_HAVE_CONTENT = new RakuElementType("POD_HAVE_CONTENT");
    IElementType POD_NEWLINE = new RakuElementType("POD_NEWLINE");
    IElementType POD_REMOVED_WHITESPACE = new RakuElementType("POD_REMOVED_WHITESPACE");
    IElementType POD_TEXT = new RakuElementType("POD_TEXT");
    IElementType POD_TYPENAME = new RakuElementType("POD_TYPENAME");
    IElementType POD_WHITESPACE = new RakuElementType("POD_WHITESPACE");
    IElementType POSTFIX = new RakuElementType("POSTFIX");
    IElementType POSTFIX_INTERPOLATIN = new RakuElementType("POSTFIX_INTERPOLATIN");
    IElementType PREFIX = new RakuElementType("PREFIX");
    IElementType QUASI = new RakuElementType("QUASI");
    IElementType QUOTE_MOD = new RakuElementType("QUOTE_MOD");
    IElementType QUOTE_PAIR = new RakuElementType("QUOTE_PAIR");
    IElementType QUOTE_REGEX = new RakuElementType("QUOTE_REGEX");
    IElementType RADIX_NUMBER = new RakuElementType("RADIX_NUMBER");
    IElementType RAT_LITERAL = new RakuElementType("RAT_LITERAL");
    IElementType REGEX_ANCHOR = new RakuElementType("REGEX_ANCHOR");
    IElementType REGEX_ASSERTION_ANGLE_CLOSE = new RakuElementType("REGEX_ASSERTION_ANGLE_CLOSE");
    IElementType REGEX_ASSERTION_ANGLE_OPEN = new RakuElementType("REGEX_ASSERTION_ANGLE_OPEN");
    IElementType REGEX_ASSERTION_END = new RakuElementType("REGEX_ASSERTION_END");
    IElementType REGEX_BACKSLASH_BAD = new RakuElementType("REGEX_BACKSLASH_BAD");
    IElementType REGEX_BUILTIN_CCLASS = new RakuElementType("REGEX_BUILTIN_CCLASS");
    IElementType REGEX_CAPTURE_NAME = new RakuElementType("REGEX_CAPTURE_NAME");
    IElementType REGEX_CAPTURE_PARENTHESES_CLOSE = new RakuElementType("REGEX_CAPTURE_PARENTHESES_CLOSE");
    IElementType REGEX_CAPTURE_PARENTHESES_OPEN = new RakuElementType("REGEX_CAPTURE_PARENTHESES_OPEN");
    IElementType REGEX_CCLASS_ATOM = new RakuElementType("REGEX_CCLASS_ATOM");
    IElementType REGEX_CCLASS_INCOMPLETE = new RakuElementType("REGEX_CCLASS_INCOMPLETE");
    IElementType REGEX_CCLASS_SYNTAX = new RakuElementType("REGEX_CCLASS_SYNTAX");
    IElementType REGEX_DECLARATOR = new RakuElementType("REGEX_DECLARATOR");
    IElementType REGEX_GROUP_BRACKET_CLOSE = new RakuElementType("REGEX_GROUP_BRACKET_CLOSE");
    IElementType REGEX_GROUP_BRACKET_OPEN = new RakuElementType("REGEX_GROUP_BRACKET_OPEN");
    IElementType REGEX_INFIX = new RakuElementType("REGEX_INFIX");
    IElementType REGEX_LOOKAROUND = new RakuElementType("REGEX_LOOKAROUND");
    IElementType REGEX_MISSING_ASSERTION = new RakuElementType("REGEX_MISSING_ASSERTION");
    IElementType REGEX_MISSING_TERM = new RakuElementType("REGEX_MISSING_TERM");
    IElementType REGEX_MOD_INTERNAL = new RakuElementType("REGEX_MOD_INTERNAL");
    IElementType REGEX_MOD_INTERNAL_NUMERIC = new RakuElementType("REGEX_MOD_INTERNAL_NUMERIC");
    IElementType REGEX_MOD_UNKNOWN = new RakuElementType("REGEX_MOD_UNKNOWN");
    IElementType REGEX_QUANTIFIER = new RakuElementType("REGEX_QUANTIFIER");
    IElementType REGEX_QUANTIFIER_MISSING = new RakuElementType("REGEX_QUANTIFIER_MISSING");
    IElementType REGEX_SIGSPACE = new RakuElementType("REGEX_SIGSPACE");
    IElementType REGEX_VARIABLE = new RakuElementType("REGEX_VARIABLE");
    IElementType REGEX_VARIABLE_BINDING = new RakuElementType("REGEX_VARIABLE_BINDING");
    IElementType REGEX_VARIABLE_BINDING_INCOMPLETE = new RakuElementType("REGEX_VARIABLE_BINDING_INCOMPLETE");
    IElementType RETURN_ARROW = new RakuElementType("RETURN_ARROW");
    IElementType ROUTINE_DECLARATOR = new RakuElementType("ROUTINE_DECLARATOR");
    IElementType ROUTINE_NAME = new RakuElementType("ROUTINE_NAME");
    IElementType SCOPE_DECLARATOR = new RakuElementType("SCOPE_DECLARATOR");
    IElementType SELF = new RakuElementType("SELF");
    IElementType SELF_CALL_VARIABLE = new RakuElementType("SELF_CALL_VARIABLE");
    IElementType SELF_CALL_VARIABLE_ARGS = new RakuElementType("SELF_CALL_VARIABLE_ARGS");
    IElementType SEMI_LIST_END = new RakuElementType("SEMI_LIST_END");
    IElementType SHAPE_DECLARATION = new RakuElementType("SHAPE_DECLARATION");
    IElementType SIGNATURE_BRACKET_CLOSE = new RakuElementType("SIGNATURE_BRACKET_CLOSE");
    IElementType SIGNATURE_BRACKET_OPEN = new RakuElementType("SIGNATURE_BRACKET_OPEN");
    IElementType SIMPLE_CONTEXTUALIZER = new RakuElementType("SIMPLE_CONTEXTUALIZER");
    IElementType STATEMENT_CONTROL = new RakuElementType("STATEMENT_CONTROL");
    IElementType STATEMENT_MOD_COND = new RakuElementType("STATEMENT_MOD_COND");
    IElementType STATEMENT_MOD_LOOP = new RakuElementType("STATEMENT_MOD_LOOP");
    IElementType STATEMENT_PREFIX = new RakuElementType("STATEMENT_PREFIX");
    IElementType STATEMENT_TERMINATOR = new RakuElementType("STATEMENT_TERMINATOR");
    IElementType STRING_LITERAL_CHAR = new RakuElementType("STRING_LITERAL_CHAR");
    IElementType STRING_LITERAL_ESCAPE = new RakuElementType("STRING_LITERAL_ESCAPE");
    IElementType STRING_LITERAL_QUOTE_CLOSE = new RakuElementType("STRING_LITERAL_QUOTE_CLOSE");
    IElementType STRING_LITERAL_QUOTE_OPEN = new RakuElementType("STRING_LITERAL_QUOTE_OPEN");
    IElementType STRING_LITERAL_QUOTE_SYNTAX = new RakuElementType("STRING_LITERAL_QUOTE_SYNTAX");
    IElementType STRING_LITERAL_REQUOTE_ESCAPE = new RakuElementType("STRING_LITERAL_REQUOTE_ESCAPE");
    IElementType STUB_CODE = new RakuElementType("STUB_CODE");
    IElementType SUBSET_ANON = new RakuElementType("SUBSET_ANON");
    IElementType SUBSET_INCOMPLETE = new RakuElementType("SUBSET_INCOMPLETE");
    IElementType SUBST_ASSIGNISH = new RakuElementType("SUBST_ASSIGNISH");
    IElementType SUB_CALL_NAME = new RakuElementType("SUB_CALL_NAME");
    IElementType TERM = new RakuElementType("TERM");
    IElementType TERM_DECLARATION_BACKSLASH = new RakuElementType("TERM_DECLARATION_BACKSLASH");
    IElementType TERM_IS_MULTI = new RakuElementType("TERM_IS_MULTI");
    IElementType TRAIT = new RakuElementType("TRAIT");
    IElementType TRAIT_INCOMPLETE = new RakuElementType("TRAIT_INCOMPLETE");
    IElementType TRANS_BAD = new RakuElementType("TRANS_BAD");
    IElementType TRANS_CHAR = new RakuElementType("TRANS_CHAR");
    IElementType TRANS_ESCAPE = new RakuElementType("TRANS_ESCAPE");
    IElementType TRANS_RANGE = new RakuElementType("TRANS_RANGE");
    IElementType TRUSTS = new RakuElementType("TRUSTS");
    IElementType TR_DISTINCT_START_STOP = new RakuElementType("TR_DISTINCT_START_STOP");
    IElementType TYPE_COERCION_PARENTHESES_CLOSE = new RakuElementType("TYPE_COERCION_PARENTHESES_CLOSE");
    IElementType TYPE_COERCION_PARENTHESES_OPEN = new RakuElementType("TYPE_COERCION_PARENTHESES_OPEN");
    IElementType TYPE_CONST = new RakuElementType("TYPE_CONST");
    IElementType TYPE_DECLARATOR = new RakuElementType("TYPE_DECLARATOR");
    IElementType TYPE_PARAMETER_BRACKET = new RakuElementType("TYPE_PARAMETER_BRACKET");
    IElementType UNSPACED_POSTFIX = new RakuElementType("UNSPACED_POSTFIX");
    IElementType UNSP_WHITE_SPACE = new RakuElementType("UNSP_WHITE_SPACE");
    IElementType UNV_WHITE_SPACE = new RakuElementType("UNV_WHITE_SPACE");
    IElementType VARIABLE = new RakuElementType("VARIABLE");
    IElementType VARIABLE_REGEX_NAMED_CAPTURE = new RakuElementType("VARIABLE_REGEX_NAMED_CAPTURE");
    IElementType VERSION = new RakuElementType("VERSION");
    IElementType VERTICAL_WHITE_SPACE = new RakuElementType("VERTICAL_WHITE_SPACE");
    IElementType WHATEVER = new RakuElementType("WHATEVER");
    IElementType WHERE_CONSTRAINT = new RakuElementType("WHERE_CONSTRAINT");
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType WS_OUTSIDE_LIST = new RakuElementType("WS_OUTSIDE_LIST");
}
