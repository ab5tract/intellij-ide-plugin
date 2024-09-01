package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuRegexGoal;
import org.jetbrains.annotations.NotNull;

public class RakuRegexGoalImpl extends ASTWrapperPsiElement implements RakuRegexGoal {
    public RakuRegexGoalImpl(@NotNull ASTNode node) {
        super(node);
    }
}
