package edument.rakuidea.psi;

public interface RakuBlock extends RakuPsiElement, RakuPsiScope, RakuExtractable {
    RakuBlockoid getBlockoid();
}
