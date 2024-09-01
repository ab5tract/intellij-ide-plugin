package edument.rakuidea.cro;

import com.intellij.psi.stubs.StubIndexKey;
import edument.rakuidea.psi.RakuSubCall;

public class CroIndexKeys {
    public static final StubIndexKey<String, RakuSubCall> CRO_ROUTES
            = StubIndexKey.createIndexKey("perl6.cro.routes");
    public static final StubIndexKey<String, RakuSubCall> CRO_TEMPLATE
            = StubIndexKey.createIndexKey("perl6.cro.template");
}
