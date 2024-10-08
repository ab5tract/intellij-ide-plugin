package org.raku.refactoring.inline.call;

import com.intellij.history.LocalHistory;
import com.intellij.history.LocalHistoryAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.usageView.UsageInfo;
import com.intellij.usageView.UsageViewDescriptor;
import org.raku.psi.*;
import org.raku.refactoring.CompleteRakuElementFactory;
import org.raku.refactoring.inline.RakuInlineProcessor;
import org.raku.refactoring.inline.RakuInlineViewDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RakuInlineRoutineProcessor extends RakuInlineProcessor {
    private final RakuRoutineDecl myRoutine;
    private final PsiElement myCall;
    private final Editor myEditor;
    private final boolean myInlineThisOnly;
    private final boolean myDeleteTheDeclaration;

    protected RakuInlineRoutineProcessor(@NotNull Project project, RakuRoutineDecl routine,
                                         @Nullable PsiElement call, Editor editor,
                                         boolean inlineThisOnly, boolean isDeleteTheDeclaration) {
        super(project);
        myRoutine = routine;
        myCall = call;
        myEditor = editor;
        myInlineThisOnly = inlineThisOnly;
        myDeleteTheDeclaration = isDeleteTheDeclaration;
    }

    @NotNull
    @Override
    protected UsageViewDescriptor createUsageViewDescriptor(@NotNull UsageInfo[] usages) {
        return new RakuInlineViewDescriptor(myRoutine);
    }

    @NotNull
    @Override
    protected UsageInfo[] findUsages() {
        if (myInlineThisOnly && myCall != null) {
            return new UsageInfo[]{new UsageInfo(myCall)};
        }
        Set<UsageInfo> usages = new HashSet<>();

        for (PsiReference reference : ReferencesSearch.search(myRoutine, GlobalSearchScope.projectScope(myProject))) {
            usages.add(new UsageInfo(reference.getElement()));
        }

        return usages.toArray(UsageInfo.EMPTY_ARRAY);
    }

    @Override
    protected void performRefactoring(@NotNull UsageInfo[] usages) {
        RangeMarker position = null;
        if (myEditor != null) {
            final int offset = myEditor.getCaretModel().getOffset();
            position = myEditor.getDocument().createRangeMarker(offset, offset + 1);
        }

        LocalHistoryAction action = LocalHistory.getInstance().startAction(getCommandName());
        try {
            doRefactoring(usages);
        }
        finally {
            action.finish();
        }

        if (position != null) {
            if (position.isValid()) {
                myEditor.getCaretModel().moveToOffset(position.getStartOffset());
            }
            position.dispose();
        }
    }

    private void doRefactoring(UsageInfo[] usages) {
        for (UsageInfo usage : usages) {
            PsiElement psiElementToInlineInto = usage.getElement();
            RakuCodeBlockCall usageElement = PsiTreeUtil.getNonStrictParentOfType(psiElementToInlineInto, RakuCodeBlockCall.class);

            if (usageElement == null)
                continue;

            PsiElement[] blockNodes = getBlockCopy(myRoutine.getContent());

            unwrapLastReturnStatement(blockNodes);

            PsiElement replacement = createReplacement(myProject, usageElement, blockNodes, myRoutine);

            PsiElement newStatement = usageElement.getWholeCallNode().replace(replacement.copy());
            PsiDocumentManager.getInstance(myProject).doPostponedOperationsAndUnblockDocument(myEditor.getDocument());
            CodeStyleManager.getInstance(myProject).reformat(newStatement);
        }

        PsiDocumentManager.getInstance(myProject).commitAllDocuments();

        if (myDeleteTheDeclaration) {
            deleteDeclaration();
        }
    }

    private void deleteDeclaration() {
        RakuStatement statement = PsiTreeUtil.getParentOfType(myRoutine, RakuStatement.class);
        if (statement == null)
            return;
        PsiElement newline = statement.getNextSibling();
        if (newline instanceof PsiWhiteSpace)
            statement.getParent().deleteChildRange(statement, newline);
        else
            statement.delete();
    }

    @NotNull
    @Override
    protected String getCommandName() {
        return "Inline routine";
    }

    @NotNull
    private static Map<String, Integer> enumeratePositionalParameters(RakuRoutineDecl decl) {
        Map<String, Integer> positionalRoutineParams = new HashMap<>();

        RakuSignature signature = decl.getSignatureNode();
        if (signature != null) {
            RakuParameter[] parameters = signature.getParameters();
            for (int i = 0, parametersLength = parameters.length; i < parametersLength; i++) {
                RakuParameter parameter = parameters[i];
                if (parameter.isPositional()) {
                    positionalRoutineParams.put(parameter.getVariableName(), i);
                }
            }
        }
        return positionalRoutineParams;
    }

    private static void unwrapLastReturnStatement(PsiElement[] blockNodes) {
        if (blockNodes.length == 0)
            return;
        PsiElement blockNode = blockNodes[blockNodes.length - 1];
        PsiElement returnStatement = blockNode.getFirstChild();
        if (returnStatement instanceof RakuSubCall && ((RakuSubCall)returnStatement).getCallName().equals("return")) {
            RakuPsiElement name = (RakuPsiElement)returnStatement.getFirstChild();
            PsiElement initializer = name.skipWhitespacesForward().copy();
            returnStatement.replace(initializer);
        }
    }

    private static PsiElement[] getBlockCopy(PsiElement[] blockNodes) {
        for (int i = 0, blockNodesLength = blockNodes.length; i < blockNodesLength; i++) {
            blockNodes[i] = blockNodes[i].copy();
        }
        return blockNodes;
    }

    private static PsiElement createReplacement(Project project, RakuCodeBlockCall call, PsiElement[] blockCopy, RakuRoutineDecl decl) {
        PsiElement inserter;

        if (blockCopy.length == 0) {
            return CompleteRakuElementFactory.createNil(project);
        } else if (blockCopy.length == 1) {
            RakuStatement singleStatement = (RakuStatement)blockCopy[0];

            inserter = singleStatement.getFirstChild().copy();
            PsiElement wholeCallNode = call.getWholeCallNode();
            if (!(wholeCallNode.getParent() instanceof RakuStatement) &&
                checkIfNeedToWrap(inserter))
                inserter = CompleteRakuElementFactory.createParenthesesExpr(inserter);
        }
        else {
            RakuDo doStatement = RakuElementFactory.createDoStatement(project);
            doStatement.addStatements(blockCopy);
            inserter = doStatement;
        }
        updateVariables(call, inserter, decl);
        return inserter;
    }

    private static void updateVariables(RakuCodeBlockCall call, PsiElement inserter, RakuRoutineDecl decl) {
        // We just use simple recursion here,
        // because variables can be pulled into inner subs and packages
        // as a closure, so we have to replace all of them except for
        // variables that are defined in this block and so are local
        Collection<RakuVariable> variables = PsiTreeUtil.findChildrenOfType(inserter, RakuVariable.class);

        Map<String, Integer> positionalRoutineParams = enumeratePositionalParameters(decl);

        List<PsiElement> callPositionalArgs = new ArrayList<>();
        Map<String, PsiElement> callNamedArgs = new HashMap<>();

        for (PsiElement arg : call.getCallArguments()) {
            if (arg instanceof RakuFatArrow) {
                callNamedArgs.put(((RakuFatArrow)arg).getKey(), ((RakuFatArrow)arg).getValue());
            }
            else {
                callPositionalArgs.add(arg);
            }
        }

        RakuSignature signatureNode = decl.getSignatureNode();
        RakuParameter[] declarationParameters = signatureNode == null ? new RakuParameter[0] : signatureNode.getParameters();

        for (RakuVariable variable : variables) {
            if (shouldKeepVariable(inserter, variable)) continue;

            // Named arguments don't have sigils, so we have to make a comparison
            // with sigilless variable name
            String variableName = variable.getVariableName();
            int sigilsCutIndex = RakuVariable.getTwigil(variableName) == ' ' ? 1 : 2;

            if (callNamedArgs.containsKey(variableName.substring(sigilsCutIndex))) {
                variable.replace(
                    wrapElement(callNamedArgs.get(variableName.substring(sigilsCutIndex)))
                        .copy()
                );
            }
            else {
                // If it is not a named variable, let's try
                Integer callIndex = positionalRoutineParams.get(variableName);
                if (callIndex == null) {
                    // TODO this is really an erroneous state, don't just skip it
                    continue;
                }
                if (callIndex >= callPositionalArgs.size()) {
                    for (RakuParameter param : declarationParameters) {
                        if (Objects.equals(param.getVariableName(), variableName) && param.getInitializer() != null) {
                            variable.replace(param.getInitializer());
                        }
                    }
                    positionalRoutineParams.get(variableName);
                }
                else {
                    PsiElement argument = callPositionalArgs.get(callIndex);
                    variable.replace(wrapElement(argument).copy());
                }
            }
        }
    }

    private static PsiElement wrapElement(PsiElement argument) {
        if (argument instanceof RakuInfixApplication) {
            return CompleteRakuElementFactory.createParenthesesExpr(argument);
        }
        return argument;
    }

    private static boolean shouldKeepVariable(PsiElement block, RakuVariable variable) {
        PsiReference ref = variable.getReference();
        assert ref != null;
        PsiElement declaration = ref.resolve();
        // If variable is not resolve-able for us, e.g. a dynamic,
        // or is declared but within the block itself, we keep it as it
        return declaration != null && PsiTreeUtil.isAncestor(block, declaration, true);
    }
}
