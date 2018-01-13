package edument.perl6idea.parsing;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import edument.perl6idea.Perl6Language;

public interface Perl6ElementTypes {
    IFileElementType FILE = new IFileElementType(Perl6Language.INSTANCE);
    IElementType ARRAY_INDEX = new Perl6ElementType("ARRAY_INDEX");
    IElementType ARRAY_SHAPE = new Perl6ElementType("ARRAY_SHAPE");
    IElementType BLOCK = new Perl6ElementType("BLOCK");
    IElementType CALL = new Perl6ElementType("CALL");
    IElementType CATCH_STATEMENT = new Perl6ElementType("CATCH_STATEMENT");
    IElementType CONTROL_STATEMENT = new Perl6ElementType("CONTROL_STATEMENT");
    IElementType DEFAULT_STATEMENT = new Perl6ElementType("DEFAULT_STATEMENT");
    IElementType DO = new Perl6ElementType("DO");
    IElementType EAGER = new Perl6ElementType("EAGER");
    IElementType EXPR = new Perl6ElementType("EXPR");
    IElementType FATARROW = new Perl6ElementType("FATARROW");
    IElementType FOR_STATEMENT = new Perl6ElementType("FOR_STATEMENT");
    IElementType GATHER = new Perl6ElementType("GATHER");
    IElementType GIVEN_STATEMENT = new Perl6ElementType("GIVEN_STATEMENT");
    IElementType HASH_INDEX = new Perl6ElementType("HASH_INDEX");
    IElementType HYPER = new Perl6ElementType("HYPER");
    IElementType HYPER_WHATEVER = new Perl6ElementType("HYPER_WHATEVER");
    IElementType IF_STATEMENT = new Perl6ElementType("IF_STATEMENT");
    IElementType IMPORT_STATEMENT = new Perl6ElementType("IMPORT_STATEMENT");
    IElementType INFIX = new Perl6ElementType("INFIX");
    IElementType INTEGER_LITERAL = new Perl6ElementType("INTEGER_LITERAL");
    IElementType LAZY = new Perl6ElementType("LAZY");
    IElementType LOOP_STATEMENT = new Perl6ElementType("LOOP_STATEMENT");
    IElementType METHOD_CALL = new Perl6ElementType("METHOD_CALL");
    IElementType MULTI_DECLARATION = new Perl6ElementType("MULTI_DECLARATION");
    IElementType NAMED_PARAMETER = new Perl6ElementType("NAMED_PARAMETER");
    IElementType NEED_STATEMENT = new Perl6ElementType("NEED_STATEMENT");
    IElementType NO_STATEMENT = new Perl6ElementType("NO_STATEMENT");
    IElementType NUMBER_LITERAL = new Perl6ElementType("NUMBER_LITERAL");
    IElementType ONCE = new Perl6ElementType("ONCE");
    IElementType PACKAGE_DECLARATION = new Perl6ElementType("PACKAGE_DECLARATION");
    IElementType PARAMETER = new Perl6ElementType("PARAMETER");
    IElementType PARAMETER_DEFAULT = new Perl6ElementType("PARAMETER_DEFAULT");
    IElementType PARAMETER_VARIABLE = new Perl6ElementType("PARAMETER_VARIABLE");
    IElementType PHASER = new Perl6ElementType("PHASER");
    IElementType POSTFIX = new Perl6ElementType("POSTFIX");
    IElementType PREFIX = new Perl6ElementType("PREFIX");
    IElementType QUIETLY = new Perl6ElementType("QUIETLY");
    IElementType QUIT_STATEMENT = new Perl6ElementType("QUIT_STATEMENT");
    IElementType RACE = new Perl6ElementType("RACE");
    IElementType RAT_LITERAL = new Perl6ElementType("RAT_LITERAL");
    IElementType REACT = new Perl6ElementType("REACT");
    IElementType REGEX = new Perl6ElementType("REGEX");
    IElementType REGEX_ATOM = new Perl6ElementType("REGEX_ATOM");
    IElementType REGEX_DECLARATION = new Perl6ElementType("REGEX_DECLARATION");
    IElementType REGEX_LITERAL = new Perl6ElementType("REGEX_LITERAL");
    IElementType REGEX_SIGSPACE = new Perl6ElementType("REGEX_SIGSPACE");
    IElementType REPEAT_STATEMENT = new Perl6ElementType("REPEAT_STATEMENT");
    IElementType REQUIRE_STATEMENT = new Perl6ElementType("REQUIRE_STATEMENT");
    IElementType ROUTINE_DECLARATION = new Perl6ElementType("ROUTINE_DECLARATION");
    IElementType SCOPED_DECLARATION = new Perl6ElementType("SCOPED_DECLARATION");
    IElementType SELF = new Perl6ElementType("SELF");
    IElementType SEMI_LIST = new Perl6ElementType("SEMI_LIST");
    IElementType SIGNATURE = new Perl6ElementType("SIGNATURE");
    IElementType SINK = new Perl6ElementType("SINK");
    IElementType START = new Perl6ElementType("START");
    IElementType STATEMENT = new Perl6ElementType("STATEMENT");
    IElementType STATEMENT_LIST = new Perl6ElementType("STATEMENT_LIST");
    IElementType STATEMENT_MOD_COND = new Perl6ElementType("STATEMENT_MOD_COND");
    IElementType STATEMENT_MOD_LOOP = new Perl6ElementType("STATEMENT_MOD_LOOP");
    IElementType STRING_LITERAL = new Perl6ElementType("STRING_LITERAL");
    IElementType SUB_CALL = new Perl6ElementType("SUB_CALL");
    IElementType SUPPLY = new Perl6ElementType("SUPPLY");
    IElementType TRY = new Perl6ElementType("TRY");
    IElementType TYPE_NAME = new Perl6ElementType("TYPE_NAME");
    IElementType UNLESS_STATEMENT = new Perl6ElementType("UNLESS_STATEMENT");
    IElementType UNTIL_STATEMENT = new Perl6ElementType("UNTIL_STATEMENT");
    IElementType USE_STATEMENT = new Perl6ElementType("USE_STATEMENT");
    IElementType VARIABLE = new Perl6ElementType("VARIABLE");
    IElementType VARIABLE_DECLARATION = new Perl6ElementType("VARIABLE_DECLARATION");
    IElementType VERSION = new Perl6ElementType("VERSION");
    IElementType WHATEVER = new Perl6ElementType("WHATEVER");
    IElementType WHENEVER_STATEMENT = new Perl6ElementType("WHENEVER_STATEMENT");
    IElementType WHEN_STATEMENT = new Perl6ElementType("WHEN_STATEMENT");
    IElementType WHERE_CONSTRAINT = new Perl6ElementType("WHERE_CONSTRAINT");
    IElementType WHILE_STATEMENT = new Perl6ElementType("WHILE_STATEMENT");
    IElementType WITHOUT_STATEMENT = new Perl6ElementType("WITHOUT_STATEMENT");
}
