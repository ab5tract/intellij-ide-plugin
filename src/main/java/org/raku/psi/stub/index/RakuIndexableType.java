package org.raku.psi.stub.index;

import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.psi.PsiElement;

@InternalIgnoreDependencyViolation
public interface RakuIndexableType extends PsiElement, NavigationItem {
}
