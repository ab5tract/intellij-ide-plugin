package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuSemiList;
import org.jetbrains.annotations.NotNull;

public class RakuSemiListImpl extends ASTWrapperPsiElement implements RakuSemiList {
    public RakuSemiListImpl(@NotNull ASTNode node) {
        super(node);
    }
}
