package edument.rakuidea.cro.template.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import edument.rakuidea.cro.template.psi.CroTemplateParameter;
import edument.rakuidea.cro.template.psi.CroTemplateSignature;
import edument.rakuidea.cro.template.psi.reference.CroTemplateSymbolCollector;
import edument.rakuidea.cro.template.psi.reference.CroTemplateSymbolKind;
import org.jetbrains.annotations.NotNull;

public class CroTemplateSignatureImpl extends ASTWrapperPsiElement implements CroTemplateSignature {
    public CroTemplateSignatureImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public void offerAllParametersTo(CroTemplateSymbolCollector collector) {
        for (CroTemplateParameter parameter : PsiTreeUtil.findChildrenOfType(this, CroTemplateParameter.class)) {
            collector.offer(parameter.getName(), CroTemplateSymbolKind.Variable, parameter);
            if (collector.isSatisfied())
                break;
        }
    }
}
