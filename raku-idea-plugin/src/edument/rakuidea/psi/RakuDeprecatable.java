package edument.rakuidea.psi;

import org.jetbrains.annotations.Nullable;

public interface RakuDeprecatable {
    boolean isDeprecated();

    @Nullable
    String getDeprecationMessage();
}
