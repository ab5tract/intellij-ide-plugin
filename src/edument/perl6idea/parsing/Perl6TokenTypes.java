package edument.perl6idea.parsing;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

public interface Perl6TokenTypes {
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
    IElementType INFIX = new Perl6ElementType("INFIX");
    IElementType INTEGER_LITERAL = new Perl6ElementType("INTEGER_LITERAL");
    IElementType INVOCANT_MARKER = new Perl6ElementType("INVOCANT_MARKER");
    IElementType LAMBDA = new Perl6ElementType("LAMBDA");
    IElementType METHOD_CALL_NAME = new Perl6ElementType("METHOD_CALL_NAME");
    IElementType METHOD_CALL_OPERATOR = new Perl6ElementType("METHOD_CALL_OPERATOR");
    IElementType MISSING_BLOCK = new Perl6ElementType("MISSING_BLOCK");
    IElementType MISSING_BLORST = new Perl6ElementType("MISSING_BLORST");
    IElementType MISSING_REGEX = new Perl6ElementType("MISSING_REGEX");
    IElementType MULTI_DECLARATOR = new Perl6ElementType("MULTI_DECLARATOR");
    IElementType NAME = new Perl6ElementType("NAME");
    IElementType NAMED_PARAMETER_NAME_ALIAS = new Perl6ElementType("NAMED_PARAMETER_NAME_ALIAS");
    IElementType NAMED_PARAMETER_SYNTAX = new Perl6ElementType("NAMED_PARAMETER_SYNTAX");
    IElementType NUMBER_LITERAL = new Perl6ElementType("NUMBER_LITERAL");
    IElementType ONLY_STAR = new Perl6ElementType("ONLY_STAR");
    IElementType PACKAGE_DECLARATOR = new Perl6ElementType("PACKAGE_DECLARATOR");
    IElementType PAIR_KEY = new Perl6ElementType("PAIR_KEY");
    IElementType PARAMETER_SEPARATOR = new Perl6ElementType("PARAMETER_SEPARATOR");
    IElementType PARENTHESES = new Perl6ElementType("PARENTHESES");
    IElementType PHASER = new Perl6ElementType("PHASER");
    IElementType POSTFIX = new Perl6ElementType("POSTFIX");
    IElementType PREFIX = new Perl6ElementType("PREFIX");
    IElementType RAT_LITERAL = new Perl6ElementType("RAT_LITERAL");
    IElementType REGEX_DECLARATOR = new Perl6ElementType("REGEX_DECLARATOR");
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
    IElementType VARIABLE = new Perl6ElementType("VARIABLE");
    IElementType VERSION = new Perl6ElementType("VERSION");
    IElementType WHATEVER = new Perl6ElementType("WHATEVER");
    IElementType WHERE_CONSTRAINT = new Perl6ElementType("WHERE_CONSTRAINT");
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
}
