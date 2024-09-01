package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuRace;
import org.jetbrains.annotations.NotNull;

public class RakuRaceImpl extends ASTWrapperPsiElement implements RakuRace {
    public RakuRaceImpl(@NotNull ASTNode node) {
        super(node);
    }
}
