package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuArrayShape;
import org.jetbrains.annotations.NotNull;

public class RakuArrayShapeImpl extends ASTWrapperPsiElement implements RakuArrayShape {
    public RakuArrayShapeImpl(@NotNull ASTNode node) {
        super(node);
    }
}
