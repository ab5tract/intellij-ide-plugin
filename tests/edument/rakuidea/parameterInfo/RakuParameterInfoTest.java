package edument.rakuidea.parameterInfo;

import com.intellij.testFramework.utils.parameterInfo.MockCreateParameterInfoContext;
import com.intellij.testFramework.utils.parameterInfo.MockParameterInfoUIContext;
import edument.rakuidea.CommaFixtureTestCase;
import edument.rakuidea.RakuParameterInfoHandler;
import edument.rakuidea.filetypes.RakuScriptFileType;
import edument.rakuidea.psi.RakuCodeBlockCall;
import edument.rakuidea.psi.RakuRoutineDecl;

import java.util.function.Consumer;

public class RakuParameterInfoTest extends CommaFixtureTestCase {
    public static final RakuParameterInfoHandler HANDLER = new RakuParameterInfoHandler();

    private void doTest(String text, String args, Consumer<MockParameterInfoUIContext<?>>... checks) {
        StringBuilder builder = new StringBuilder();
        for (String signature : text.split(" \\|\\|\\| "))
            builder.append(String.format("multi a(%s); ", signature));
        builder.append("a(").append(args).append("<caret>");
        doTest(builder.toString(), checks);
    }

    private void doTest(String text, Consumer<MockParameterInfoUIContext<?>>... checks) {
        myFixture.configureByText(RakuScriptFileType.INSTANCE, text);
        MockCreateParameterInfoContext createContext = new MockCreateParameterInfoContext(myFixture.getEditor(), myFixture.getFile());
        RakuCodeBlockCall owner = HANDLER.findElementForParameterInfo(createContext);
        HANDLER.showParameterInfo(owner, createContext);
        Object[] items = createContext.getItemsToShow();
        assertNotNull(items);
        assertTrue(items.length != 0);
        MockParameterInfoUIContext<?> uiContext = new MockParameterInfoUIContext<>(owner);
        assertEquals(items.length, checks.length);
        for (int i = 0; i < items.length; i++) {
            Object item = items[i];
            HANDLER.updateUI((RakuRoutineDecl)item, uiContext);
            checks[i].accept(uiContext);
        }
    }

    private static void assertParameterInfo(MockParameterInfoUIContext<?> context, boolean isEnabled, String text, int start, int end) {
        assertEquals(text, context.getText());
        assertEquals(isEnabled, context.isUIComponentEnabled());
        assertEquals(start, context.getHighlightStart());
        assertEquals(end, context.getHighlightEnd());
    }

    public void testPosVsNamedSingle() {
        doTest("$a ||| :$foo", "",
               context -> assertParameterInfo(context,true, "$a", 0, 2),
               context -> assertParameterInfo(context, true, ":$foo", 0, 5));
    }

    public void testSingleArg() {
        doTest("$a ||| :$b", "42,",
               context -> assertParameterInfo(context, true, "$a", 0, 0),
               context -> assertParameterInfo(context, false, ":$b", 0, 0));
    }

    public void testNamedIsAnticipated() {
        doTest("$a ||| $a, :$b", "42,",
               context -> assertParameterInfo(context, true, "$a", 0, 0),
               context -> assertParameterInfo(context, true, "$a, :$b", 4, 7));
    }

    public void testOptionalIsAnticipated() {
        doTest("$a ||| $a, $b?", "42,",
               context -> assertParameterInfo(context, true, "$a", 0, 0),
               context -> assertParameterInfo(context, true, "$a, $b?", 4, 7));
    }

    public void testSlurpyIsAnticipated() {
        doTest("$a, *@b ||| $a, $b", "42, 43, 44,",
               context -> assertParameterInfo(context, true, "$a, *@b", 4, 7),
               context -> assertParameterInfo(context, false, "$a, $b", 0, 0));
    }

    public void testMethodParameterInfo() {
        doTest("class A { multi method a($a) {}; multi method a($a, :$foo) {}; multi method a(:$best) { self.a(:!best<caret> } }; ",
               context -> assertParameterInfo(context, true, "$a",0, 2),
               context -> assertParameterInfo(context, true, "$a, :$foo",0, 2),
               context -> assertParameterInfo(context, true, ":$best",0, 6));
    }

    public void testOffset() {
        doTest("Int $tran, Int $dataset, Str $module-key, Hash :$defaults?, :$apply-defaults = True --> Int", "1, 2,",
               context -> assertParameterInfo(context, true, "Int $tran, Int $dataset, Str $module-key, Hash :$defaults?, :$apply-defaults = True", 25, 40));
    }

    public void testJumping() {
        doTest("$a, $b, $asdf", "1, 3",
               context -> assertParameterInfo(context, true, "$a, $b, $asdf", 4, 6));
    }

//    public void testExternalParameterInfo() {
//        doTest("slurp(<caret>",
//               context -> assertParameterInfo(context, true, "IO::Handle:D $fh = { ... }, |c is raw", 0, 26),
//               context -> assertParameterInfo(context, true, "$path, |c is raw", 0, 5));
//    }

    public void testSyntheticConstructor() {
        // Synthetic constructor is disabled if explicit one
        doTest("class A { has $.foo; has $.bar; method new($a, $b, :$c) {} }; A.new(<caret>)",
               context -> assertParameterInfo(context, true, "$a, $b, :$c", 0, 2));
        // Synthetic constructor is disabled if explicit is in parent
        doTest("class A { method new($a, $b) {} }; class B is A { has $.foo; has $.bar; }; B.new(<caret>)",
               context -> assertParameterInfo(context, true, "$a, $b", 0, 2));
        // Synthetic constructor is enabled
        doTest("class A { has $.foo; has Str $.bar; method test($a, $b, :$c) {} }; A.new(<caret>)",
               context -> assertParameterInfo(context, true, "Any :$foo, Str :$bar", 0, 9));
    }
}
