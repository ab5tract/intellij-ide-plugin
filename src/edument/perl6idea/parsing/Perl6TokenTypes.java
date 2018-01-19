package edument.perl6idea.parsing;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

public interface Perl6TokenTypes {
    IElementType ARGLIST_EMPTY = new Perl6ElementType("ARGLIST_EMPTY");
    IElementType ARGLIST_END = new Perl6ElementType("ARGLIST_END");
    IElementType ARGLIST_START = new Perl6ElementType("ARGLIST_START");
    IElementType ARRAY_COMPOSER = new Perl6ElementType("ARRAY_COMPOSER");
    IElementType ARRAY_INDEX_BRACKET = new Perl6ElementType("ARRAY_INDEX_BRACKET");
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType BAD_ESCAPE = new Perl6ElementType("BAD_ESCAPE");
    IElementType BLOCK_CURLY_BRACKET = new Perl6ElementType("BLOCK_CURLY_BRACKET");
    IElementType COMMENT = new Perl6ElementType("COMMENT");
    IElementType END_OF_EXPR = new Perl6ElementType("END_OF_EXPR");
    IElementType END_OF_PARAMETERS = new Perl6ElementType("END_OF_PARAMETERS");
    IElementType END_OF_STATEMENT = new Perl6ElementType("END_OF_STATEMENT");
    IElementType HASH_INDEX_BRACKET = new Perl6ElementType("HASH_INDEX_BRACKET");
    IElementType HYPER_WHATEVER = new Perl6ElementType("HYPER_WHATEVER");
    IElementType INCOMPLETE_TYPE_NAME = new Perl6ElementType("INCOMPLETE_TYPE_NAME");
    IElementType INFIX = new Perl6ElementType("INFIX");
    IElementType INITIALIZER_MISSING = new Perl6ElementType("INITIALIZER_MISSING");
    IElementType INTEGER_LITERAL = new Perl6ElementType("INTEGER_LITERAL");
    IElementType INVOCANT_MARKER = new Perl6ElementType("INVOCANT_MARKER");
    IElementType LAMBDA = new Perl6ElementType("LAMBDA");
    IElementType METHOD_CALL_NAME = new Perl6ElementType("METHOD_CALL_NAME");
    IElementType METHOD_CALL_OPERATOR = new Perl6ElementType("METHOD_CALL_OPERATOR");
    IElementType MISSING_BLOCK = new Perl6ElementType("MISSING_BLOCK");
    IElementType MISSING_BLORST = new Perl6ElementType("MISSING_BLORST");
    IElementType MISSING_REGEX = new Perl6ElementType("MISSING_REGEX");
    IElementType MISSING_RETURN_CONSTRAINT = new Perl6ElementType("MISSING_RETURN_CONSTRAINT");
    IElementType MULTI_DECLARATOR = new Perl6ElementType("MULTI_DECLARATOR");
    IElementType NAME = new Perl6ElementType("NAME");
    IElementType NAMED_PARAMETER_NAME_ALIAS = new Perl6ElementType("NAMED_PARAMETER_NAME_ALIAS");
    IElementType NAMED_PARAMETER_SYNTAX = new Perl6ElementType("NAMED_PARAMETER_SYNTAX");
    IElementType NO_ARGS = new Perl6ElementType("NO_ARGS");
    IElementType NUMBER_LITERAL = new Perl6ElementType("NUMBER_LITERAL");
    IElementType ONLY_STAR = new Perl6ElementType("ONLY_STAR");
    IElementType PACKAGE_DECLARATOR = new Perl6ElementType("PACKAGE_DECLARATOR");
    IElementType PAIR_KEY = new Perl6ElementType("PAIR_KEY");
    IElementType PARAMETER_ANON = new Perl6ElementType("PARAMETER_ANON");
    IElementType PARAMETER_INCOMPLETE = new Perl6ElementType("PARAMETER_INCOMPLETE");
    IElementType PARAMETER_QUANTIFIER = new Perl6ElementType("PARAMETER_QUANTIFIER");
    IElementType PARAMETER_SEPARATOR = new Perl6ElementType("PARAMETER_SEPARATOR");
    IElementType PARENTHESES = new Perl6ElementType("PARENTHESES");
    IElementType PHASER = new Perl6ElementType("PHASER");
    IElementType POSTFIX = new Perl6ElementType("POSTFIX");
    IElementType PREFIX = new Perl6ElementType("PREFIX");
    IElementType RAT_LITERAL = new Perl6ElementType("RAT_LITERAL");
    IElementType REGEX_ANCHOR = new Perl6ElementType("REGEX_ANCHOR");
    IElementType REGEX_ASSERTION_ANGLE = new Perl6ElementType("REGEX_ASSERTION_ANGLE");
    IElementType REGEX_ASSERTION_END = new Perl6ElementType("REGEX_ASSERTION_END");
    IElementType REGEX_BACKSLASH_BAD = new Perl6ElementType("REGEX_BACKSLASH_BAD");
    IElementType REGEX_BUILTIN_CCLASS = new Perl6ElementType("REGEX_BUILTIN_CCLASS");
    IElementType REGEX_CAPTURE_NAME = new Perl6ElementType("REGEX_CAPTURE_NAME");
    IElementType REGEX_CAPTURE_PARENTHESES = new Perl6ElementType("REGEX_CAPTURE_PARENTHESES");
    IElementType REGEX_CCLASS_ATOM = new Perl6ElementType("REGEX_CCLASS_ATOM");
    IElementType REGEX_CCLASS_INCOMPLETE = new Perl6ElementType("REGEX_CCLASS_INCOMPLETE");
    IElementType REGEX_CCLASS_SYNTAX = new Perl6ElementType("REGEX_CCLASS_SYNTAX");
    IElementType REGEX_DECLARATOR = new Perl6ElementType("REGEX_DECLARATOR");
    IElementType REGEX_GROUP_BRACKET = new Perl6ElementType("REGEX_GROUP_BRACKET");
    IElementType REGEX_INFIX = new Perl6ElementType("REGEX_INFIX");
    IElementType REGEX_LOOKAROUND = new Perl6ElementType("REGEX_LOOKAROUND");
    IElementType REGEX_MISSING_ASSERTION = new Perl6ElementType("REGEX_MISSING_ASSERTION");
    IElementType REGEX_MISSING_TERM = new Perl6ElementType("REGEX_MISSING_TERM");
    IElementType REGEX_QUANTIFIER = new Perl6ElementType("REGEX_QUANTIFIER");
    IElementType REGEX_QUANTIFIER_MISSING = new Perl6ElementType("REGEX_QUANTIFIER_MISSING");
    IElementType RETURN_ARROW = new Perl6ElementType("RETURN_ARROW");
    IElementType ROUTINE_DECLARATOR = new Perl6ElementType("ROUTINE_DECLARATOR");
    IElementType ROUTINE_NAME = new Perl6ElementType("ROUTINE_NAME");
    IElementType SCOPE_DECLARATOR = new Perl6ElementType("SCOPE_DECLARATOR");
    IElementType SELF = new Perl6ElementType("SELF");
    IElementType SEMI_LIST_END = new Perl6ElementType("SEMI_LIST_END");
    IElementType STATEMENT_CONTROL = new Perl6ElementType("STATEMENT_CONTROL");
    IElementType STATEMENT_MOD_COND = new Perl6ElementType("STATEMENT_MOD_COND");
    IElementType STATEMENT_MOD_LOOP = new Perl6ElementType("STATEMENT_MOD_LOOP");
    IElementType STATEMENT_PREFIX = new Perl6ElementType("STATEMENT_PREFIX");
    IElementType STATEMENT_TERMINATOR = new Perl6ElementType("STATEMENT_TERMINATOR");
    IElementType STRING_LITERAL_CHAR = new Perl6ElementType("STRING_LITERAL_CHAR");
    IElementType STRING_LITERAL_ESCAPE = new Perl6ElementType("STRING_LITERAL_ESCAPE");
    IElementType STRING_LITERAL_QUOTE = new Perl6ElementType("STRING_LITERAL_QUOTE");
    IElementType SUB_CALL_NAME = new Perl6ElementType("SUB_CALL_NAME");
    IElementType TERM = new Perl6ElementType("TERM");
    IElementType TRAIT = new Perl6ElementType("TRAIT");
    IElementType TRAIT_INCOMPLETE = new Perl6ElementType("TRAIT_INCOMPLETE");
    IElementType TYPE_COERCION_PARENTHESES = new Perl6ElementType("TYPE_COERCION_PARENTHESES");
    IElementType TYPE_PARAMETER_BRACKET = new Perl6ElementType("TYPE_PARAMETER_BRACKET");
    IElementType VARIABLE = new Perl6ElementType("VARIABLE");
    IElementType VERSION = new Perl6ElementType("VERSION");
    IElementType WHATEVER = new Perl6ElementType("WHATEVER");
    IElementType WHERE_CONSTRAINT = new Perl6ElementType("WHERE_CONSTRAINT");
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
}
