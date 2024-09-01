package edument.rakuidea.refactoring;

import com.intellij.refactoring.classMembers.MemberInfoBase;
import edument.rakuidea.psi.RakuPsiDeclaration;

public class RakuAttributeInfo extends MemberInfoBase<RakuPsiDeclaration> {
    private final RakuPsiDeclaration myDeclaration;

    public RakuAttributeInfo(RakuPsiDeclaration member) {
        super(member);
        myDeclaration = member;
    }

    @Override
    public String getDisplayName() {
        return myDeclaration.getName();
    }
}
