package org.raku.findUsages;

import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.psi.PsiElement;
import com.intellij.usages.impl.rules.UsageType;
import com.intellij.usages.impl.rules.UsageTypeProvider;
import org.raku.psi.*;
import org.jetbrains.annotations.Nullable;

@InternalIgnoreDependencyViolation
public class RakuUsageTypeProvider implements UsageTypeProvider {
    public static final UsageType SUBROUTINE_CALL_USAGE = new UsageType(() -> "Subroutine call usage");
    public static final UsageType METHOD_CALL_USAGE = new UsageType(() -> "Method call usage");
    public static final UsageType VARIABLE_USAGE = new UsageType(() -> "Variable usage");
    public static final UsageType INHERITANCE_USAGE = new UsageType(() ->"Inheritance usage");
    public static final UsageType DECLARATION_TYPE_RESTRICTION_USAGE = new UsageType(() ->"Declaration type restriction usage");
    public static final UsageType PARAMETER_TYPE_RESTRICTION_USAGE = new UsageType(() ->"Parameter type restriction usage");
    public static final UsageType RETURN_TYPE_RESTRICTION_USAGE = new UsageType(() -> "Return type restriction usage");
    public static final UsageType COMPOSITION_USAGE = new UsageType(() -> "Composition usage");
    public static final UsageType TRAIT_USAGE = new UsageType(() -> "Trait usage");
    public static final UsageType USE_MODULE_USAGE = new UsageType(() -> "Use module usage");
    public static final UsageType NEED_MODULE_USAGE = new UsageType(() ->"Need module usage");

    @Nullable
    @Override
    public UsageType getUsageType(PsiElement element) {
        if (element == null) return null;

        if (element instanceof RakuTypeName) {
            return handleTypeName(element);
        }
        else if (element instanceof RakuIsTraitName) {
            return handleIsTraitName();
        }
        else if (element instanceof RakuVariable) {
            return handleVariable();
        }
        else if (element instanceof RakuMethodCall) {
            return handleMethodCall();
        }
        else if (element instanceof RakuSubCallName) {
            return handleSubCallName();
        }
        else if (element instanceof RakuModuleName) {
            return handleModuleName(element);
        }

        return null;
    }

    private static UsageType handleModuleName(PsiElement element) {
        PsiElement parent = element.getParent();
        if (parent instanceof RakuUseStatement) {
            return USE_MODULE_USAGE;
        }
        else if (parent instanceof RakuNeedStatement) {
            return NEED_MODULE_USAGE;
        }
        return null;
    }

    private static UsageType handleIsTraitName() {
        return INHERITANCE_USAGE;
    }

    private static UsageType handleSubCallName() {
        return SUBROUTINE_CALL_USAGE;
    }

    private static UsageType handleMethodCall() {
        return METHOD_CALL_USAGE;
    }

    private static UsageType handleVariable() {
        return VARIABLE_USAGE;
    }

    private static UsageType handleTypeName(PsiElement element) {
        PsiElement parent = element.getParent();
        if (parent instanceof RakuScopedDecl) {
            return DECLARATION_TYPE_RESTRICTION_USAGE;
        }
        else if (parent instanceof RakuParameter) {
            return PARAMETER_TYPE_RESTRICTION_USAGE;
        }
        else if (parent instanceof RakuReturnConstraint) {
            return RETURN_TYPE_RESTRICTION_USAGE;
        }
        else if (parent instanceof RakuTrait) {
            if (((RakuTrait)parent).getTraitModifier().equals("does")) {
                return COMPOSITION_USAGE;
            }
            else {
                return TRAIT_USAGE;
            }
        }
        return null;
    }
}
