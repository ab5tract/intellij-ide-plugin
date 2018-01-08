package edument.perl6idea.parsing;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

public interface Perl6TokenTypes {
    IElementType END_OF_PARAMETERS = new Perl6ElementType("END_OF_PARAMETERS");
    IElementType HYPER_WHATEVER = new Perl6ElementType("HYPER_WHATEVER");
    IElementType NAMED_PARAMETER_NAME_ALIAS = new Perl6ElementType("NAMED_PARAMETER_NAME_ALIAS");
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType STRING_LITERAL_QUOTE = new Perl6ElementType("STRING_LITERAL_QUOTE");
    IElementType NAME = new Perl6ElementType("NAME");
    IElementType MULTI_DECLARATOR = new Perl6ElementType("MULTI_DECLARATOR");
    IElementType PHASER = new Perl6ElementType("PHASER");
    IElementType INVOCANT_MARKER = new Perl6ElementType("INVOCANT_MARKER");
    IElementType POSTFIX = new Perl6ElementType("POSTFIX");
    IElementType STATEMENT_MOD_COND = new Perl6ElementType("STATEMENT_MOD_COND");
    IElementType PREFIX = new Perl6ElementType("PREFIX");
    IElementType ARRAY_INDEX_BRACKET = new Perl6ElementType("ARRAY_INDEX_BRACKET");
    IElementType STRING_LITERAL_CHAR = new Perl6ElementType("STRING_LITERAL_CHAR");
    IElementType STATEMENT_MOD_LOOP = new Perl6ElementType("STATEMENT_MOD_LOOP");
    IElementType VERSION = new Perl6ElementType("VERSION");
    IElementType SELF = new Perl6ElementType("SELF");
    IElementType HASH_INDEX_BRACKET = new Perl6ElementType("HASH_INDEX_BRACKET");
    IElementType BAD_ESCAPE = new Perl6ElementType("BAD_ESCAPE");
    IElementType STATEMENT_PREFIX = new Perl6ElementType("STATEMENT_PREFIX");
    IElementType RAT_LITERAL = new Perl6ElementType("RAT_LITERAL");
    IElementType METHOD_CALL_OPERATOR = new Perl6ElementType("METHOD_CALL_OPERATOR");
    IElementType ROUTINE_DECLARATOR = new Perl6ElementType("ROUTINE_DECLARATOR");
    IElementType PACKAGE_DECLARATOR = new Perl6ElementType("PACKAGE_DECLARATOR");
    IElementType NUMBER_LITERAL = new Perl6ElementType("NUMBER_LITERAL");
    IElementType INTEGER_LITERAL = new Perl6ElementType("INTEGER_LITERAL");
    IElementType STATEMENT_CONTROL = new Perl6ElementType("STATEMENT_CONTROL");
    IElementType STATEMENT_TERMINATOR = new Perl6ElementType("STATEMENT_TERMINATOR");
    IElementType BLOCK_CURLY_BRACKET = new Perl6ElementType("BLOCK_CURLY_BRACKET");
    IElementType PARENTHESES = new Perl6ElementType("PARENTHESES");
    IElementType VARIABLE = new Perl6ElementType("VARIABLE");
    IElementType INFIX = new Perl6ElementType("INFIX");
    IElementType NAMED_PARAMETER_SYNTAX = new Perl6ElementType("NAMED_PARAMETER_SYNTAX");
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType SEMI_LIST_END = new Perl6ElementType("SEMI_LIST_END");
    IElementType SUB_CALL_NAME = new Perl6ElementType("SUB_CALL_NAME");
    IElementType MISSING_BLORST = new Perl6ElementType("MISSING_BLORST");
    IElementType LAMBDA = new Perl6ElementType("LAMBDA");
    IElementType COMMENT = new Perl6ElementType("COMMENT");
    IElementType METHOD_CALL_NAME = new Perl6ElementType("METHOD_CALL_NAME");
    IElementType END_OF_STATEMENT = new Perl6ElementType("END_OF_STATEMENT");
    IElementType SCOPE_DECLARATOR = new Perl6ElementType("SCOPE_DECLARATOR");
    IElementType PARAMETER_SEPARATOR = new Perl6ElementType("PARAMETER_SEPARATOR");
    IElementType ONLY_STAR = new Perl6ElementType("ONLY_STAR");
    IElementType WHATEVER = new Perl6ElementType("WHATEVER");
    IElementType END_OF_EXPR = new Perl6ElementType("END_OF_EXPR");
    IElementType STRING_LITERAL_ESCAPE = new Perl6ElementType("STRING_LITERAL_ESCAPE");
    IElementType ROUTINE_NAME = new Perl6ElementType("ROUTINE_NAME");
    IElementType WHERE_CONSTRAINT = new Perl6ElementType("WHERE_CONSTRAINT");
}
