package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.parsing.RakuTokenTypes;
import edument.rakuidea.psi.*;
import edument.rakuidea.psi.RakuStrLiteral;
import edument.rakuidea.psi.RakuTrait;
import edument.rakuidea.psi.stub.RakuTraitStub;
import edument.rakuidea.psi.stub.RakuTraitStubElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static edument.rakuidea.parsing.RakuElementTypes.*;

public class RakuTraitImpl extends StubBasedPsiElementBase<RakuTraitStub> implements RakuTrait {
    public RakuTraitImpl(@NotNull ASTNode node) {
        super(node);
    }

    public RakuTraitImpl(RakuTraitStub stub, RakuTraitStubElementType type) {
        super(stub, type);
    }

    @Override
    public String getTraitModifier() {
        RakuTraitStub stub = getStub();
        if (stub != null)
            return stub.getTraitModifier();

        PsiElement trait = findChildByType(RakuTokenTypes.TRAIT);
        return trait == null ? "" : trait.getText();
    }

    @Override
    public String getTraitName() {
        RakuTraitStub stub = getStub();
        if (stub != null)
            return stub.getTraitName();

        PsiElement isName = findChildByType(IS_TRAIT_NAME);
        if (isName != null) return isName.getText();

        PsiElement typeName = findChildByType(TYPE_NAME);
        if (typeName != null) return typeName.getText();

        RakuStrLiteral strLiteral = PsiTreeUtil.getChildOfType(this, RakuStrLiteral.class);
        if (strLiteral != null) return strLiteral.getStringText();

        return "";
    }

    @Override
    public void changeTraitMod(String newMod) {
        replace(RakuElementFactory.createTrait(getProject(), newMod, getTraitName()));
    }

    @Nullable
    @Override
    public RakuTypeName getCompositionTypeName() {
        return findChildByType(TYPE_NAME);
    }

    public String toString() {
        return getClass().getSimpleName() + "(Perl6:TRAIT)";
    }
}
