package edument.perl6idea.intention;

import com.intellij.codeInsight.intention.IntentionAction;
import edument.perl6idea.CommaFixtureTestCase;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IntentionTest extends CommaFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "perl6-idea-plugin/testData/intention";
    }

    public void testZeroToN() {
        executeIntention("Use simpler range syntax");
    }

    public void testZeroToExclusiveN() {
        executeIntention("Use simpler range syntax");
    }

    public void testZeroToVar() {
        executeIntention("Use simpler range syntax");
    }

    public void testZeroToExclusiveVar() {
        executeIntention("Use simpler range syntax");
    }

    public void testZeroToExclusiveVarInParentheses() {
        executeIntention("Use simpler range syntax");
    }

    public void testEVALOfVariable() {
        executeIntention("Add MONKEY");
    }

    public void testEVALOfInterpolation() {
        executeIntention("Add MONKEY");
    }

    public void testRoleMethodsStubbing() {
        executeIntention("Stub");
    }

    public void testRecursiveRoleMethodsStubbing() {
        executeIntention("Stub");
    }

    public void testMyVariableExport() {
        executeIntention("Change scope");
    }

    public void testRoleDoesClassConvertedToInheritance() {
        executeIntention("Replace \"does\"");
    }

    public void testUnitPrependingForNamedClass() {
        executeIntention("Add missing");
    }

    public void testUnitRemovalForDefinedGrammar() {
        executeIntention("Remove 'unit'");
    }

    public void testPrivateMethodStubbing() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingWithoutEnclosingRoutine() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingInNestedRoutines() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingSignatureGeneration() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingSignatureGenerationForSingleArg() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingSignatureGenerationColonpair() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingSignatureGenerationForSingleArgColonpair() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingSignatureGenerationForNamedParameters() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingFromUnfinishedVariable() {
        executeIntention("Create");
    }

    public void testOrderOfNamedVariablesInCallIsFixed() {
        executeIntention("Create");
    }

    public void testConflictResolutionAfterMoveSolved() {
        executeIntention("Create");
    }

    public void testPrivateMethodStubbingReformatsOnlyAddedBlock() {
        executeIntention("Create");
    }

    public void testPrivateMethodHasPrivateVariableArgumentUpdated() {
        executeIntention("Create");
    }

    public void testColonPairSimplification() {
        executeIntention("Convert");
    }

    public void testFatArrowSimplification() {
        executeIntention("Convert");
    }

    public void testWhileOneSimplification() {
        executeIntention("Use");
    }

    public void testWhileTrueSimplification() {
        executeIntention("Use");
    }

    public void testConstConstantKeywordFix() {
        executeIntention("Use");
    }

    public void testConstConstantVarFix() {
        executeIntention("Use");
    }

    public void testConstSubFix() {
        checkIntentionAbsence("Use");
    }

    public void testWithConstructionFix() {
        executeIntention("Use");
    }

    public void testPackageTypeChangeIntention() {
        executeIntention("Change");
    }

    public void testPackageTypeChangeIntoMonitorIntention() throws InterruptedException {
        ensureModuleIsLoaded("OO::Monitors");
        executeIntention("Change");
    }

    public void testPackageTypeChangeIntoMonitorPresent() throws InterruptedException {
        ensureModuleIsLoaded("OO::Monitors");
        executeIntention("Change");
    }

    public void testPackageTypeChangeOnTypeNameIntention() {
        executeIntention("Change");
    }

    public void testPackageTypeChangesInheritanceIntention() {
        executeIntention("Change");
    }

    public void testPackageTypeChangesInheritanceComposition() {
        executeIntention("Change");
    }

    public void testAttributeRequiredOnlyHas() {
        checkIntentionAbsence("Make required");
    }

    public void testAttributeRequiredNoDoubling() {
        checkIntentionAbsence("Make required");
    }

    public void testAttributeRequiredNoTraits() {
        executeIntention("Make required");
    }

    public void testAttributeRequiredTraits() {
        executeIntention("Make required");
    }

    public void testAttributeRequiredOnName() {
        executeIntention("Make required");
    }

    public void testAttributeRequiredWithDefault() {
        checkIntentionAbsence("Make required");
    }

    public void testWithoutConstructionFix() {
        executeIntention("Use");
    }

    public void testWithConstructionMultiFix() {
        executeIntention("Use");
    }

    public void testGrepFirstFixWhateverSingle() {
        executeIntention("Replace");
    }

    public void testGrepFirstFixWhateverMany() {
        executeIntention("Replace");
    }

    public void testGrepFirstFixBlockMany() {
        executeIntention("Replace");
    }

    public void testMakeMethodPublicIntention() {
        executeIntention("Make");
    }

    public void testMakeMethodPublicOnNameIntention() {
        executeIntention("Make");
    }

    public void testMakeMethodPublicIntentionIsForPrivateOnly() {
        checkIntentionAbsence("Make");
    }

    public void testMakeMethodSubmethod() {
        executeIntention("Make submethod");
    }

    public void testArrayInitializationRemoval() {
        executeIntention("Remove redundant");
    }

    public void testAwaitAllOfUnwrapArray() {
        executeIntention("Unwrap Promise.allof");
    }

    public void testAwaitAllOfUnwrapInfix() {
        executeIntention("Unwrap Promise.allof");
    }

    public void testAwaitAllOfUnwrapPrefix() {
        executeIntention("Unwrap Promise.allof");
    }

    public void testPerl6ExecutableStrFix() {
        executeIntention("Use $*EXECUTABLE");
    }

    public void testUnparenSimple() {
        executeIntention("Remove parentheses");
    }

    public void testUnparenInfix() {
        executeIntention("Remove parentheses");
    }

    public void testUnparenInitializer() {
        executeIntention("Remove parentheses");
    }

    public void testEmptyUnparenNotAllowed() {
        checkIntentionAbsence("Remove parentheses");
    }

    public void testBindingDestructuringFix() {
        executeIntention("Use binding");
    }

    private void checkIntentionAbsence(String hint) {
        assertNull(prepareIntention(hint));
    }

    private void executeIntention(String hint) {
        IntentionAction intention = prepareIntention(hint);
        assertNotNull(intention);
        myFixture.launchAction(intention);
        myFixture.checkResultByFile(getTestName(false) + ".p6", true);
    }

    @Nullable
    private IntentionAction prepareIntention(String hint) {
        myFixture.configureByFile(getTestName(false) + "Before.p6");
        List<IntentionAction> availableIntentions = myFixture.filterAvailableIntentions(hint);
        assertTrue(availableIntentions.size() == 1 || availableIntentions.size() == 0);
        if (availableIntentions.size() == 0)
            return null;
        else
            return myFixture.findSingleIntention(hint);
    }
}
