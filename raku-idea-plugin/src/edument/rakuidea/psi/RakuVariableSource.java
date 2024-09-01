package edument.rakuidea.psi;

public interface RakuVariableSource extends RakuPsiElement, RakuPsiDeclaration {
    String[] getVariableNames();
    RakuVariable[] getVariables();
}
