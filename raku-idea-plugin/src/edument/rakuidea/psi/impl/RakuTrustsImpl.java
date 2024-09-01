package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.RakuTrusts;
import edument.rakuidea.psi.RakuTypeName;

public class RakuTrustsImpl extends ASTWrapperPsiElement implements RakuTrusts {
    public RakuTrustsImpl(ASTNode node) {
        super(node);
    }

    @Override
    public String getTypeName() {
        RakuTypeName name = PsiTreeUtil.findChildOfType(this, RakuTypeName.class);
        return name == null ? "" : name.getTypeName();
    }
}
