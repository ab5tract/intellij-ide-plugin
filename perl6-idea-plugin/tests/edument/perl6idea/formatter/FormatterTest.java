package edument.perl6idea.formatter;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import edument.perl6idea.CommaFixtureTestCase;
import edument.perl6idea.Perl6Language;
import edument.perl6idea.filetypes.Perl6ScriptFileType;

import java.util.function.BiConsumer;

public class FormatterTest extends CommaFixtureTestCase {
    @Override
    protected String getTestDataPath() {
        return "perl6-idea-plugin/testData/formatter";
    }

    public void testIndentation() {
        // Normal indent cases
        reformatTest("my <caret>$a;", "my <caret>$a;");
        reformatTest("if True {\nsay 42;\n}", "if True {\n    say 42;\n}");
        reformatTest("if True {\nsay 42;\n}", "if True {\n     say 42;\n}",
                     (s1, s2) -> s1.getIndentOptions().INDENT_SIZE = 5);
        // Continuation indent cases
        reformatTest("my $a =\n42;", "my $a =\n        42;");
        reformatTest("my $a = 42\n.bar().baz();", "my $a = 42\n        .bar().baz();");
        reformatTest("push\n@foo, 42;", "push\n        @foo, 42;");
        reformatTest("@foo.push:\n42;", "@foo.push:\n        42;");
        reformatTest("basic-indent");
        reformatTest("basic-class");
        reformatTest("class ABC\n  is export {}", "class ABC\n        is export {}");
    }

    public void testSpacing() {
    }

    public void testAlignment() {
        // Expressions
        reformatTest("say 1 +\n4;", "say 1 +\n    4;");
        reformatTest("foobaz 1 +\n4;", "foobaz 1 +\n       4;");
        // Array, hash literals
        reformatTest("my @ab = 12,2333,3,4,\n5,6,7;", "my @ab = 12, 2333, 3, 4,\n         5, 6, 7;");
        // TODO when we have a better processing of this literal
        // reformatTest("my @ab = <12 2333 3 4\n5 6 7>;", "my @ab = <12 2333 3 4\n         5 6 7>;");
        reformatTest("my %long-hash = a => 42,\nb => 50;", "my %long-hash = a => 42,\n                b => 50;");
        reformatTest("basic-hash");
        // traits
        reformatTest("traits");
        // parameters, calls
        reformatTest("sub foobar($first-one,\n$second-one) {}", "sub foobar($first-one,\n           $second-one) {}");
        reformatTest("push 42,\n42", "push 42,\n     42");
        // call chains
        reformatTest("Foo\n.method(42)\n.method2(24)", "Foo\n        .method(42)\n        .method2(24)");
    }

    public void testWrapping() {
        // literals
        // call chains
        // parameters
        // args
        // traits
    }

    public void testEnterIndentation() {
    }

    public void testIntegrationCases() {
        reformatTest("basic");
        reformatTest("assorted");
        reformatTest("grammar-basic");
        reformatTest("break-lines");
        reformatTest("blocks", (s1, s2) -> s1.KEEP_SIMPLE_BLOCKS_IN_ONE_LINE = false);
        reformatTest("class A {};", "class A {\n\n};", (s1, s2) -> s2.PACKAGE_DECLARATION_IN_ONE_LINE = false);
        reformatTest("space-before-semi");
        reformatTest("hash");
        reformatTest("hash-multiline-values");
        reformatTest("array", (s1, s2) -> s1.getIndentOptions().CONTINUATION_INDENT_SIZE = 4);
        reformatTest("trailing-comma");
        reformatTest("comments-left-intact");
    }

    /* -- HELPERS -- */

    /**
     * These methods are used to supplement testing of implicit reformatting (guided by an enter pressing)
     */
    private void enterTest(String filename) {
        myFixture.configureByFile(filename + ".in.p6");
        CommandProcessor.getInstance().executeCommand(getProject(), () -> {
            EditorActionManager actionManager = EditorActionManager.getInstance();
            EditorActionHandler enterHandler = actionManager.getActionHandler(IdeActions.ACTION_EDITOR_ENTER);
            enterHandler.execute(myFixture.getEditor(), null, DataManager.getInstance().getDataContextFromFocus().getResult());
        }, "", null);
        myFixture.checkResultByFile(filename + ".out.p6");
    }

    /**
     * These methods are used to supplement testing of explicit reformatting (guided by an action).
     */
    private void reformatTest(String input, String output) {
        reformatTest(input, output, (s1, s2) -> {});
    }

    private void reformatTest(String input, String output, BiConsumer<CommonCodeStyleSettings, Perl6CodeStyleSettings> config) {
        myFixture.configureByText(Perl6ScriptFileType.INSTANCE, input);
        reformat(config);
        myFixture.checkResult(output);
    }

    private void reformatTest(String filename) {
        reformatTest(filename, (s1, s2) -> {});
    }

    private void reformatTest(String filename, BiConsumer<CommonCodeStyleSettings, Perl6CodeStyleSettings> config) {
        myFixture.configureByFiles(filename + ".in.p6");
        reformat(config);
        myFixture.checkResultByFile(filename + ".out.p6");
    }

    private void reformat(BiConsumer<CommonCodeStyleSettings, Perl6CodeStyleSettings> config) {
        WriteCommandAction.runWriteCommandAction(null, () -> {
            FormatManager formatManager = new FormatManager();
            formatManager.updateTempSettings(config);
            formatManager.reformatAndResetSettings(myFixture.getFile());
        });
    }

    private class FormatManager {
        private CodeStyleManager myManager;
        private CodeStyleSettingsManager mySettingsManager;
        private CodeStyleSettings myTemp;
        private CodeStyleSettings myOriginalSettigns;

        public FormatManager() {
            myManager = CodeStyleManager.getInstance(myFixture.getProject());
            mySettingsManager = CodeStyleSettingsManager.getInstance(myFixture.getProject());
            myTemp = mySettingsManager.getTemporarySettings();
            myOriginalSettigns = myTemp.clone();
        }

        public void updateTempSettings(BiConsumer<CommonCodeStyleSettings, Perl6CodeStyleSettings> config) {
            CommonCodeStyleSettings commons = myTemp.getCommonSettings(Perl6Language.INSTANCE);
            Perl6CodeStyleSettings customs = myTemp.getCustomSettings(Perl6CodeStyleSettings.class);
            config.accept(commons, customs);
        }

        public void reformatAndResetSettings(PsiFile file) {
            mySettingsManager.setTemporarySettings(myTemp);
            myManager.reformat(file);
            mySettingsManager.setTemporarySettings(myOriginalSettigns);
        }
    }
}
