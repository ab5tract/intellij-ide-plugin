package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuOnlyStar;
import org.jetbrains.annotations.NotNull;

public class RakuOnlyStarImpl extends ASTWrapperPsiElement implements RakuOnlyStar {
    public RakuOnlyStarImpl(@NotNull ASTNode node) {
        super(node);
    }
}
