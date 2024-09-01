package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.psi.RakuRegexCall;
import edument.rakuidea.psi.RakuRegexCallReference;
import org.jetbrains.annotations.NotNull;

public class RakuRegexCallImpl extends ASTWrapperPsiElement implements RakuRegexCall {
    public RakuRegexCallImpl(ASTNode node) {
        super(node);
    }

    @Override
    public String getName() {
        return this.getText();
    }

    @Override
    public PsiReference getReference() {
        return new RakuRegexCallReference(this);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        RakuRegexCall newCall = PsiTreeUtil.findChildOfType(RakuElementFactory.createRegexCall(getProject(), name), RakuRegexCall.class);
        if (newCall != null)
            replace(newCall);
        return this;
    }
}
