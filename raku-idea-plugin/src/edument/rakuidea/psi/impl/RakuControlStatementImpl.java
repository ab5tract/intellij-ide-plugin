package edument.rakuidea.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import edument.rakuidea.psi.RakuControlStatement;
import edument.rakuidea.psi.type.RakuType;
import edument.rakuidea.sdk.RakuSdkType;
import edument.rakuidea.sdk.RakuSettingTypeId;
import org.jetbrains.annotations.NotNull;

public class RakuControlStatementImpl extends ASTWrapperPsiElement implements RakuControlStatement {
    public RakuControlStatementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public RakuType inferTopicType() {
        return RakuSdkType.getInstance().getCoreSettingType(getProject(), RakuSettingTypeId.Exception);
    }
}
