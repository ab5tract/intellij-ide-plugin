package edument.perl6idea.psi.impl;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.util.PsiTreeUtil;
import edument.perl6idea.parsing.Perl6TokenTypes;
import edument.perl6idea.psi.*;
import edument.perl6idea.psi.stub.*;
import edument.perl6idea.psi.symbols.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static edument.perl6idea.parsing.Perl6ElementTypes.TYPE_NAME;

public class Perl6TraitImpl extends StubBasedPsiElementBase<Perl6TraitStub> implements Perl6Trait {
    public Perl6TraitImpl(@NotNull ASTNode node) {
        super(node);
    }

    public Perl6TraitImpl(Perl6TraitStub stub, Perl6TraitStubElementType type) {
        super(stub, type);
    }

    @Override
    public String getTraitModifier() {
        Perl6TraitStub stub = getStub();
        if (stub != null)
            return stub.getTraitModifier();

        PsiElement trait = findChildByType(Perl6TokenTypes.TRAIT);
        return trait == null ? "" : trait.getText();
    }

    @Override
    public String getTraitName() {
        Perl6TraitStub stub = getStub();
        if (stub != null)
            return stub.getTraitName();

        PsiElement name = findChildByType(TYPE_NAME);
        return name == null ? "" : name.getText();
    }

    public String toString() {
        return getClass().getSimpleName() + "(Perl6:TRAIT)";
    }
}
