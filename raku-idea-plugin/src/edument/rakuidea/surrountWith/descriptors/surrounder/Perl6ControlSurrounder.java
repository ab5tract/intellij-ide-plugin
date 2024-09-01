package edument.rakuidea.surrountWith.descriptors.surrounder;

import com.intellij.psi.PsiElement;
import edument.rakuidea.psi.RakuControl;

public abstract class Perl6ControlSurrounder<T extends RakuControl> extends Perl6Surrounder<T> {
    public Perl6ControlSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected PsiElement insertStatements(T surrounder, PsiElement[] statements) {
        surrounder.addStatements(statements);
        return null;
    }

    @Override
    protected PsiElement getAnchor(T surrounder) {
        return surrounder.getTopic();
    }
}
