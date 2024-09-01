package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import edument.rakuidea.parsing.RakuTokenTypes;
import edument.rakuidea.psi.RakuStrLiteral;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.sdk.RakuSdkType;
import edument.rakuidea.sdk.RakuSettingTypeId;
import org.jetbrains.annotations.NotNull;

public class RakuStrLiteralImpl extends ASTWrapperPsiElement implements RakuStrLiteral {
    public RakuStrLiteralImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull RakuType inferType() {
        return RakuSdkType.getInstance().getCoreSettingType(getProject(), RakuSettingTypeId.Str);
    }

    @Override
    public String getStringText() {
        PsiElement opener = findChildByType(edument.rakuidea.parsing.RakuTokenTypes.STRING_LITERAL_QUOTE_OPEN);
        PsiElement closer = findChildByType(RakuTokenTypes.STRING_LITERAL_QUOTE_CLOSE);
        int start = opener != null ? opener.getStartOffsetInParent() + opener.getTextLength() : 0;
        int end = closer != null ? closer.getStartOffsetInParent() : this.getTextLength();
        return getText().substring(start, end);
    }
}
