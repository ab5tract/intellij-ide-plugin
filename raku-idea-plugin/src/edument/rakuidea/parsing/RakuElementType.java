package edument.rakuidea.parsing;

import com.intellij.psi.tree.IElementType;
import edument.rakuidea.RakuLanguage;
import org.jetbrains.annotations.NonNls;

public class RakuElementType extends IElementType {
    public RakuElementType(@NonNls String debugName) {
        super(debugName, RakuLanguage.INSTANCE);
    }

    public String toString() {
        return "Perl6:" + super.toString();
    }
}
