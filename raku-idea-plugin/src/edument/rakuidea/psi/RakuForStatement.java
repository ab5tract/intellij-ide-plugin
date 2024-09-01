package edument.rakuidea.psi;

import edument.rakuidea.psi.type.RakuType;

public interface RakuForStatement extends RakuPsiElement, RakuExtractable, RakuControl, RakuTopicalizer {
    RakuPsiElement getSource();
    RakuType inferLoopParameterType(int index);
}
