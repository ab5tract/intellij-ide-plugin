package edument.rakuidea.psi.stub.index;

import com.intellij.psi.stubs.StubIndexKey;
import edument.rakuidea.psi.*;

public class RakuStubIndexKeys {
    public static final StubIndexKey<String, RakuFile> PROJECT_MODULES
        = StubIndexKey.createIndexKey("perl6.projectModules");
    public static final StubIndexKey<String, RakuIndexableType> GLOBAL_TYPES
        = StubIndexKey.createIndexKey("perl6.globalTypes");
    public static final StubIndexKey<String, RakuIndexableType> LEXICAL_TYPES
        = StubIndexKey.createIndexKey("perl6.lexicalTypes");
    public static final StubIndexKey<String, RakuConstant> ALL_CONSTANTS
        = StubIndexKey.createIndexKey("perl6.allConstants");
    public static final StubIndexKey<String, RakuVariableDecl> ALL_ATTRIBUTES
        = StubIndexKey.createIndexKey("perl6.allAttributes");
    public static final StubIndexKey<String, RakuRoutineDecl> ALL_ROUTINES
        = StubIndexKey.createIndexKey("perl6.allRoutines");
    public static final StubIndexKey<String, RakuRegexDecl> ALL_REGEXES
        = StubIndexKey.createIndexKey("perl6.allRegexes");
    public static final StubIndexKey<String, RakuVariableDecl> DYNAMIC_VARIABLES
        = StubIndexKey.createIndexKey("perl6.dynamivVariables");
}
