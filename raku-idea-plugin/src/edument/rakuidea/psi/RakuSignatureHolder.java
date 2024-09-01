package edument.rakuidea.psi;

import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.psi.type.RakuUnresolvedType;
import edument.rakuidea.psi.type.RakuUntyped;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RakuSignatureHolder extends RakuMultiHolder {
    String getSignature();
    @Nullable
    RakuSignature getSignatureNode();
    @Nullable
    String getReturnsTrait();

    default String summarySignature() {
        RakuSignature signature = getSignatureNode();
        RakuType returnType = getReturnType();
        if (signature != null)
            return signature.summary(returnType);

        return returnType instanceof RakuUntyped ? "()" : "(--> " + returnType.getName() + ")";
    }

    default @NotNull RakuType getReturnType() {
        String retTrait = getReturnsTrait();
        if (retTrait != null)
            return new RakuUnresolvedType(retTrait);

        RakuSignature signature = getSignatureNode();
        if (signature == null)
            return RakuUntyped.INSTANCE;
        RakuReturnConstraint constraint = PsiTreeUtil.getChildOfType(signature, RakuReturnConstraint.class);
        if (constraint == null)
            return RakuUntyped.INSTANCE;
        return constraint.getReturnType();
    }
}
