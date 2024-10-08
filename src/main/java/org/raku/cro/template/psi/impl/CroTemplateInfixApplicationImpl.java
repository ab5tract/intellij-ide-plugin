package org.raku.cro.template.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.raku.cro.template.psi.CroTemplateInfixApplication;
import org.jetbrains.annotations.NotNull;

public class CroTemplateInfixApplicationImpl extends ASTWrapperPsiElement implements CroTemplateInfixApplication {
    public CroTemplateInfixApplicationImpl(@NotNull ASTNode node) {
        super(node);
    }
}
