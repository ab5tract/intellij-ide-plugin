package org.raku.cro;

import com.intellij.psi.stubs.StubIndexKey;
import org.raku.psi.RakuSubCall;

public class CroIndexKeys {
    public static final StubIndexKey<String, RakuSubCall> CRO_ROUTES
            = StubIndexKey.createIndexKey("rakuidea.cro.routes");
    public static final StubIndexKey<String, RakuSubCall> CRO_TEMPLATE
            = StubIndexKey.createIndexKey("rakuidea.cro.template");
}
