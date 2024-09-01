package edument.rakuidea.surrountWith.descriptors.surrounder;

import edument.rakuidea.psi.RakuTry;

public class Perl6TryCatchDefaultSurrounder extends Perl6GenericTrySurrounder<RakuTry> {
    public Perl6TryCatchDefaultSurrounder(boolean isStatement) {
        super(isStatement);
    }

    @Override
    protected String createBranch() {
        return "default {}";
    }

    @Override
    public String getTemplateDescription() {
        return "try { CATCH { default } }";
    }
}
