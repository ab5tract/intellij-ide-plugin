package edument.rakuidea.psi;

import edument.rakuidea.psi.type.RakuType;
import org.jetbrains.annotations.NotNull;

public interface RakuReturnConstraint extends RakuPsiElement {
    @NotNull
    RakuType getReturnType();
}
