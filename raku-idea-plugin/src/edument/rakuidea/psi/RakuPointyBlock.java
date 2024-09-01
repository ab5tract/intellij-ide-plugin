package edument.rakuidea.psi;

public interface RakuPointyBlock extends RakuPsiElement, RakuPsiScope, RakuExtractable, RakuControl {
    RakuParameter[] getParams();
    String getLambda();
}
