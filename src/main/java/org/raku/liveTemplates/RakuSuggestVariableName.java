package org.raku.liveTemplates;

import com.intellij.codeInsight.lookup.LookupFocusDegree;
import com.intellij.codeInsight.template.*;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import org.raku.psi.RakuPsiElement;
import org.raku.psi.RakuVariableDecl;
import org.raku.psi.symbols.RakuSymbolKind;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class RakuSuggestVariableName extends MacroBase {
  public RakuSuggestVariableName() {
    super("rakuSuggestVariableName", "rakuSuggestVariableName(String)");
  }

  @Override
  public @NotNull String getDefaultValue() {
    return "$a";
  }

  @Override
  protected @Nullable Result calculateResult(Expression @NotNull [] params, ExpressionContext context, boolean quick) {
    PsiFile file = PsiDocumentManager.getInstance(context.getProject()).getPsiFile(context.getEditor().getDocument());
    RakuPsiElement psi = PsiTreeUtil.getParentOfType(file.findElementAt(context.getStartOffset()), RakuPsiElement.class);
    String name = params[0].calculateResult(context).toString();
    if (psi == null) {
      return null;
    }
    TextRange temporaryTextRange = new TextRange(context.getTemplateStartOffset(), context.getTemplateEndOffset());
    List<@NlsSafe String> names = psi.getLexicalSymbolVariants(RakuSymbolKind.Variable)
      .stream()
      .map(s -> s.getPsi())
      .filter(p -> p instanceof RakuVariableDecl)
      .filter(v -> v.getTextRange() != null)
      // Exclude code that was previously added
      .filter(v -> !(temporaryTextRange.contains(v.getTextRange()) && file.equals(v.getContainingFile())))
      .map(p -> ((RakuVariableDecl)p).getName())
      .filter(Objects::nonNull)
      .toList();
    for (int i = 0; i < 500; i++) {
      String potentialName = name + (i == 0 ? "" : i);
      if (!names.contains(potentialName)) {
        return new TextResult(potentialName);
      }
    }
    return new TextResult(name);
  }

  @Override
  public boolean isAcceptableInContext(TemplateContextType context) {
    return context instanceof RakuContext;
  }

  @NotNull
  @Override
  public LookupFocusDegree getLookupFocusDegree() {
    return LookupFocusDegree.UNFOCUSED;
  }
}