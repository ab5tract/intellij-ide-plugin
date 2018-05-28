package edument.perl6idea.parsing;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IStubFileElementType;
import com.intellij.psi.stubs.IStubElementType;
import edument.perl6idea.psi.stub.*;

public interface Perl6ElementTypes {
    IStubFileElementType FILE = new Perl6FileElementType();
    IStubElementType PACKAGE_DECLARATION = new Perl6PackageDeclStubElementType();
    IStubElementType ROUTINE_DECLARATION = new Perl6RoutineDeclStubElementType();
    IStubElementType ENUM = new Perl6EnumStubElementType();
    IStubElementType SUBSET = new Perl6SubsetStubElementType();
    IStubElementType CONSTANT = new Perl6ConstantStubElementType();
    IStubElementType REGEX_DECLARATION = new Perl6RegexDeclStubElementType();
    IStubElementType VARIABLE_DECLARATION = new Perl6VariableDeclStubElementType();
    IStubElementType SCOPED_DECLARATION = new Perl6ScopedDeclStubElementType();
    IStubElementType USE_STATEMENT = new Perl6UseStatementStubElementType();
    IStubElementType NEED_STATEMENT = new Perl6NeedStatementStubElementType();
    IElementType ARRAY_COMPOSER = new Perl6ElementType("ARRAY_COMPOSER");
    IElementType ARRAY_INDEX = new Perl6ElementType("ARRAY_INDEX");
    IElementType ARRAY_SHAPE = new Perl6ElementType("ARRAY_SHAPE");
    IElementType ASSIGN_METAOP = new Perl6ElementType("ASSIGN_METAOP");
    IElementType BLOCK = new Perl6ElementType("BLOCK");
    IElementType BLOCKOID = new Perl6ElementType("BLOCKOID");
    IElementType BLOCK_OR_HASH = new Perl6ElementType("BLOCK_OR_HASH");
    IElementType BRACKETED_INFIX = new Perl6ElementType("BRACKETED_INFIX");
    IElementType CALL = new Perl6ElementType("CALL");
    IElementType CAPTURE = new Perl6ElementType("CAPTURE");
    IElementType CATCH_STATEMENT = new Perl6ElementType("CATCH_STATEMENT");
    IElementType COLON_PAIR = new Perl6ElementType("COLON_PAIR");
    IElementType COMPLEX_LITERAL = new Perl6ElementType("COMPLEX_LITERAL");
    IElementType CONTEXTUALIZER = new Perl6ElementType("CONTEXTUALIZER");
    IElementType CONTROL_STATEMENT = new Perl6ElementType("CONTROL_STATEMENT");
    IElementType CROSS_METAOP = new Perl6ElementType("CROSS_METAOP");
    IElementType DEFAULT_STATEMENT = new Perl6ElementType("DEFAULT_STATEMENT");
    IElementType DO = new Perl6ElementType("DO");
    IElementType EAGER = new Perl6ElementType("EAGER");
    IElementType FATARROW = new Perl6ElementType("FATARROW");
    IElementType FOR_STATEMENT = new Perl6ElementType("FOR_STATEMENT");
    IElementType GATHER = new Perl6ElementType("GATHER");
    IElementType GIVEN_STATEMENT = new Perl6ElementType("GIVEN_STATEMENT");
    IElementType HASH_INDEX = new Perl6ElementType("HASH_INDEX");
    IElementType HEREDOC = new Perl6ElementType("HEREDOC");
    IElementType HYPER = new Perl6ElementType("HYPER");
    IElementType HYPER_METAOP = new Perl6ElementType("HYPER_METAOP");
    IElementType HYPER_WHATEVER = new Perl6ElementType("HYPER_WHATEVER");
    IElementType IF_STATEMENT = new Perl6ElementType("IF_STATEMENT");
    IElementType IMPORT_STATEMENT = new Perl6ElementType("IMPORT_STATEMENT");
    IElementType INFIX = new Perl6ElementType("INFIX");
    IElementType INTEGER_LITERAL = new Perl6ElementType("INTEGER_LITERAL");
    IElementType IS_TRAIT_NAME = new Perl6ElementType("IS_TRAIT_NAME");
    IElementType LAZY = new Perl6ElementType("LAZY");
    IElementType LONG_NAME = new Perl6ElementType("LONG_NAME");
    IElementType LOOP_STATEMENT = new Perl6ElementType("LOOP_STATEMENT");
    IElementType METHOD_CALL = new Perl6ElementType("METHOD_CALL");
    IElementType MODULE_NAME = new Perl6ElementType("MODULE_NAME");
    IElementType MULTI_DECLARATION = new Perl6ElementType("MULTI_DECLARATION");
    IElementType NAMED_PARAMETER = new Perl6ElementType("NAMED_PARAMETER");
    IElementType NEGATION_METAOP = new Perl6ElementType("NEGATION_METAOP");
    IElementType NO_STATEMENT = new Perl6ElementType("NO_STATEMENT");
    IElementType NULL_TERM = new Perl6ElementType("NULL_TERM");
    IElementType NUMBER_LITERAL = new Perl6ElementType("NUMBER_LITERAL");
    IElementType ONCE = new Perl6ElementType("ONCE");
    IElementType ONLY_STAR = new Perl6ElementType("ONLY_STAR");
    IElementType OPERATOR_ADVERB = new Perl6ElementType("OPERATOR_ADVERB");
    IElementType PARAMETER = new Perl6ElementType("PARAMETER");
    IElementType PARAMETER_DEFAULT = new Perl6ElementType("PARAMETER_DEFAULT");
    IElementType PARAMETER_VARIABLE = new Perl6ElementType("PARAMETER_VARIABLE");
    IElementType PARENTHESIZED_EXPRESSION = new Perl6ElementType("PARENTHESIZED_EXPRESSION");
    IElementType PHASER = new Perl6ElementType("PHASER");
    IElementType POINTY_BLOCK = new Perl6ElementType("POINTY_BLOCK");
    IElementType POSTFIX = new Perl6ElementType("POSTFIX");
    IElementType PREFIX = new Perl6ElementType("PREFIX");
    IElementType QUIETLY = new Perl6ElementType("QUIETLY");
    IElementType QUIT_STATEMENT = new Perl6ElementType("QUIT_STATEMENT");
    IElementType QUOTE_PAIR = new Perl6ElementType("QUOTE_PAIR");
    IElementType QUOTE_REGEX = new Perl6ElementType("QUOTE_REGEX");
    IElementType RACE = new Perl6ElementType("RACE");
    IElementType RADIX_NUMBER = new Perl6ElementType("RADIX_NUMBER");
    IElementType RAT_LITERAL = new Perl6ElementType("RAT_LITERAL");
    IElementType REACT = new Perl6ElementType("REACT");
    IElementType REDUCE_METAOP = new Perl6ElementType("REDUCE_METAOP");
    IElementType REGEX = new Perl6ElementType("REGEX");
    IElementType REGEX_ANCHOR = new Perl6ElementType("REGEX_ANCHOR");
    IElementType REGEX_ASSERTION = new Perl6ElementType("REGEX_ASSERTION");
    IElementType REGEX_ATOM = new Perl6ElementType("REGEX_ATOM");
    IElementType REGEX_BUILTIN_CCLASS = new Perl6ElementType("REGEX_BUILTIN_CCLASS");
    IElementType REGEX_CAPTURE_POSITIONAL = new Perl6ElementType("REGEX_CAPTURE_POSITIONAL");
    IElementType REGEX_CCLASS = new Perl6ElementType("REGEX_CCLASS");
    IElementType REGEX_CCLASS_ELEM = new Perl6ElementType("REGEX_CCLASS_ELEM");
    IElementType REGEX_GOAL = new Perl6ElementType("REGEX_GOAL");
    IElementType REGEX_GROUP = new Perl6ElementType("REGEX_GROUP");
    IElementType REGEX_LITERAL = new Perl6ElementType("REGEX_LITERAL");
    IElementType REGEX_MOD_INTERNAL = new Perl6ElementType("REGEX_MOD_INTERNAL");
    IElementType REGEX_QUANTIFIER = new Perl6ElementType("REGEX_QUANTIFIER");
    IElementType REGEX_SEPARATOR = new Perl6ElementType("REGEX_SEPARATOR");
    IElementType REGEX_SIGSPACE = new Perl6ElementType("REGEX_SIGSPACE");
    IElementType REGEX_VARIABLE = new Perl6ElementType("REGEX_VARIABLE");
    IElementType REPEAT_STATEMENT = new Perl6ElementType("REPEAT_STATEMENT");
    IElementType REQUIRE_STATEMENT = new Perl6ElementType("REQUIRE_STATEMENT");
    IElementType RETURN_CONSTRAINT = new Perl6ElementType("RETURN_CONSTRAINT");
    IElementType REVERSE_METAOP = new Perl6ElementType("REVERSE_METAOP");
    IElementType SELF = new Perl6ElementType("SELF");
    IElementType SEMI_LIST = new Perl6ElementType("SEMI_LIST");
    IElementType SEQUENTIAL_METAOP = new Perl6ElementType("SEQUENTIAL_METAOP");
    IElementType SIGNATURE = new Perl6ElementType("SIGNATURE");
    IElementType SINK = new Perl6ElementType("SINK");
    IElementType START = new Perl6ElementType("START");
    IElementType STATEMENT = new Perl6ElementType("STATEMENT");
    IElementType STATEMENT_LIST = new Perl6ElementType("STATEMENT_LIST");
    IElementType STATEMENT_MOD_COND = new Perl6ElementType("STATEMENT_MOD_COND");
    IElementType STATEMENT_MOD_LOOP = new Perl6ElementType("STATEMENT_MOD_LOOP");
    IElementType STRING_LITERAL = new Perl6ElementType("STRING_LITERAL");
    IElementType STUB_CODE = new Perl6ElementType("STUB_CODE");
    IElementType SUB_CALL = new Perl6ElementType("SUB_CALL");
    IElementType SUPPLY = new Perl6ElementType("SUPPLY");
    IElementType TERM = new Perl6ElementType("TERM");
    IElementType TERM_DEFINITION = new Perl6ElementType("TERM_DEFINITION");
    IElementType TRAIT = new Perl6ElementType("TRAIT");
    IElementType TRANSLITERATION = new Perl6ElementType("TRANSLITERATION");
    IElementType TRY = new Perl6ElementType("TRY");
    IElementType TYPE_NAME = new Perl6ElementType("TYPE_NAME");
    IElementType UNLESS_STATEMENT = new Perl6ElementType("UNLESS_STATEMENT");
    IElementType UNTIL_STATEMENT = new Perl6ElementType("UNTIL_STATEMENT");
    IElementType VALUE_CONSTRAINT = new Perl6ElementType("VALUE_CONSTRAINT");
    IElementType VARIABLE = new Perl6ElementType("VARIABLE");
    IElementType VERSION = new Perl6ElementType("VERSION");
    IElementType WHATEVER = new Perl6ElementType("WHATEVER");
    IElementType WHENEVER_STATEMENT = new Perl6ElementType("WHENEVER_STATEMENT");
    IElementType WHEN_STATEMENT = new Perl6ElementType("WHEN_STATEMENT");
    IElementType WHERE_CONSTRAINT = new Perl6ElementType("WHERE_CONSTRAINT");
    IElementType WHILE_STATEMENT = new Perl6ElementType("WHILE_STATEMENT");
    IElementType WITHOUT_STATEMENT = new Perl6ElementType("WITHOUT_STATEMENT");
    IElementType ZIP_METAOP = new Perl6ElementType("ZIP_METAOP");
}
