package edument.rakuidea.psi;

public interface RakuRegexInfixApplication extends RakuRegexPsiElement {
    String getOperator();
    RakuRegexAtom[][] getOperandAtomSequences();
}
