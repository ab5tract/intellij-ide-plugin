package edument.perl6idea.psi;

public interface Perl6PointyBlock extends Perl6PsiElement, Perl6PsiScope, P6Extractable {
    Perl6Parameter[] getParams();
}
