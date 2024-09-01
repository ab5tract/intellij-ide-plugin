package edument.rakuidea;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

public class RakuLanguage extends Language {
    public static final RakuLanguage INSTANCE = new RakuLanguage();

    private RakuLanguage() {
        super("Perl6");
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Raku";
    }
}
