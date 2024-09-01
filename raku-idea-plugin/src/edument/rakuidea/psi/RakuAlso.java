package edument.rakuidea.psi;

import edument.rakuidea.psi.symbols.RakuMOPSymbolContributor;

public interface RakuAlso extends RakuPsiElement, RakuMOPSymbolContributor {
    RakuTrait getTrait();
}
