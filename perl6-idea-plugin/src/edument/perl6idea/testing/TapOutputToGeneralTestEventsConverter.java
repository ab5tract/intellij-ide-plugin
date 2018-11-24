package edument.perl6idea.testing;

import com.intellij.execution.process.ProcessOutputTypes;
import com.intellij.execution.testframework.TestConsoleProperties;
import com.intellij.execution.testframework.sm.ServiceMessageBuilder;
import com.intellij.execution.testframework.sm.runner.OutputToGeneralTestEventsConverter;
import com.intellij.openapi.util.Key;
import jetbrains.buildServer.messages.serviceMessages.ServiceMessageVisitor;
import org.jetbrains.annotations.NotNull;
import org.tap4j.consumer.TapConsumer;
import org.tap4j.consumer.TapConsumerException;
import org.tap4j.consumer.TapConsumerFactory;
import org.tap4j.model.*;
import org.tap4j.util.DirectiveValues;
import org.tap4j.util.StatusValues;

import java.text.ParseException;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TapOutputToGeneralTestEventsConverter extends OutputToGeneralTestEventsConverter {
    private final String myBaseUrl;
    @NotNull
    private TapConsumer myConsumer;
    private String currentTap = "";
    private ServiceMessageVisitor myVisitor;
    private String currentFile = "";

    public TapOutputToGeneralTestEventsConverter(@NotNull String testFrameworkName,
                                                 @NotNull TestConsoleProperties consoleProperties,
                                                 String url) {
        super(testFrameworkName, consoleProperties);
        myConsumer = TapConsumerFactory.makeTap13YamlConsumer();
        myBaseUrl = url;
    }

    @Override
    protected boolean processServiceMessages(String text, final Key outputType, final ServiceMessageVisitor visitor) throws ParseException {
        if (visitor != null && myVisitor == null) {
            myVisitor = visitor;
            String theMatrix = new ServiceMessageBuilder("enteredTheMatrix").toString();
            handleMessageSend(theMatrix);
        }

        if (outputType == ProcessOutputTypes.STDOUT || outputType == ProcessOutputTypes.STDERR) {
            if (text.startsWith("====")) {
                currentFile = text.substring(4, text.length() - 1);
                if (!currentTap.isEmpty())
                    processTapOutput();
            } else {
                currentTap += text;
            }
        } else if (outputType == ProcessOutputTypes.SYSTEM && text.equals("\n")) {
            // Last time.
            processTapOutput();
        }
        return true;
    }

    private void processTapOutput() throws ParseException {
        if (!currentTap.isEmpty()) {
            TestSet set;
            String testSuiteStarted = ServiceMessageBuilder
                .testSuiteStarted(currentFile)
                .addAttribute("locationHint", myBaseUrl + "/" + currentFile)
                .toString();
            handleMessageSend(testSuiteStarted);
            try {
                set = myConsumer.load(currentTap);
                processTestsCount(set);
                processSingleSuite(set.getTapLines());
            } catch (TapConsumerException e) {
                processBreakage();
            }
            handleMessageSend(ServiceMessageBuilder.testSuiteFinished(currentFile).toString());
            currentTap = "";
        }
    }

    private void processBreakage() throws ParseException {
        String name = "Test file died";
        handleMessageSend(ServiceMessageBuilder.testStarted(name).toString());
        String message = ServiceMessageBuilder.testFailed(name)
                                              .addAttribute("message", String.format("%s", currentTap)).toString();
        handleMessageSend(message);
        handleMessageSend(ServiceMessageBuilder.testFinished(name).toString());
    }

    private void processSingleSuite(List<TapElement> results) throws ParseException {
        // Apparently, Team City test protocol does not allow us
        // to send more than 1 `stdOutput` message, so we need to collect
        // all in a single string. `StringJoinger` here gives us neater
        // handling of `\n` between lines and uses fast StringBuilder internally
        StringJoiner stdOut = new StringJoiner("\n");

        // We calculate what TestElement is last (if there is one)
        // to later attach to it not only preceding text, but also following
        int lastTestIndex = -1;
        for (int i = 0, size = results.size(); i < size; i++) {
            TapElement res = results.get(i);
            if (res instanceof TestResult) {
                lastTestIndex = i;
            }
        }

        // We iterate though test suite here,
        // if iterated one is not last, we append possible
        // stdout output to it, but if we are at last one,
        // we break, because we want to collect all text output
        // that comes after it.
        TestResult savedLastTest = null;
        for (int i = 0, size = results.size(); i < size; i++) {
            TapElement result = results.get(i);

            if (i == lastTestIndex) {
                savedLastTest = (TestResult)result;
                break;
            }

            if (result instanceof TestResult) {
                processSingleTest((TestResult)result, (stdOut.length() == 0) ? null : stdOut.toString() + "\n");
                stdOut = new StringJoiner("\n");
            }
            else if (result instanceof Text) {
                stdOut.add(((Text)result).getValue());
            }
        }

        /* Here we possibly can have:
         * `saveLastTest` can be null or not, depending on if there are any tests at all
         * `stdOut` can be empty or not, depending on if there is an output before last test
         * If we have no tests at all, but all `stdOut`, then create a dummy one and post it
         * If we have last test and it is a last element in `results` array, just send it with possible `stdOut`
         * If we have last test and it is not a last element, collect all output lines after it and send
         */

        if (savedLastTest == null && stdOut.length() != 0) {
            // Create a dummy test to contain output,
            // because testSuite cannot have an output sent
            savedLastTest = new TestResult(StatusValues.OK, 0);
            savedLastTest.setDescription("Output");
        }
        if (lastTestIndex != -1 && lastTestIndex != results.size() - 1) {
            // last test is present && more output follows after it
            for (int i = lastTestIndex + 1, size = results.size(); i < size; i++) {
                TapElement result = results.get(i);
                if (result instanceof Text)
                    stdOut.add(((Text)results.get(i)).getValue());
            }
        }
        if (savedLastTest != null)
            processSingleTest(savedLastTest, (stdOut.length() == 0) ? null : stdOut.toString() + "\n");
    }

    private void processTestsCount(TestSet set) throws ParseException {
        String message = new ServiceMessageBuilder("testCount")
                .addAttribute("count", String.valueOf(set.getNumberOfTestResults())).toString();
        handleMessageSend(message);
    }

    private void processSingleTest(TestResult testResult, String stdOut) throws ParseException {
        String testName = String.format("%d %s", testResult.getTestNumber(), testResult.getDescription());
        Directive directive = testResult.getDirective();
        boolean hasSubtests = testResult.getSubtest() != null;
        if (hasSubtests) {
            handleMessageSend(ServiceMessageBuilder.testSuiteStarted(testName).toString());
            for (TestResult sub : testResult.getSubtest().getTestResults())
                processSingleTest(sub, null);
        } else {
            handleMessageSend(ServiceMessageBuilder.testStarted(testName).toString());
            if (stdOut != null) {
                ServiceMessageBuilder testStdOut = ServiceMessageBuilder.testStdOut(testName);
                testStdOut.addAttribute("out", stdOut);
                handleMessageSend(testStdOut.toString());
            }
        }

        //noinspection StatementWithEmptyBody
        if (!hasSubtests && testResult.getStatus() == StatusValues.OK &&
            testResult.getDirective() == null) {
        } else if (!hasSubtests && ((directive != null && directive.getDirectiveValue() == DirectiveValues.SKIP) ||
                   (directive != null && directive.getDirectiveValue() == DirectiveValues.TODO))) {
            String testIgnored = ServiceMessageBuilder.testIgnored(testName)
                    .addAttribute("message", String.format("%s %s", testName, testResult.getDirective().getReason())).toString();
            handleMessageSend(testIgnored);
        } else if (!hasSubtests && testResult.getStatus() == StatusValues.NOT_OK) {
            StringBuilder errorMessage = new StringBuilder(testResult.getDescription() + "\n");
            for (Comment comment : testResult.getComments()) {
                errorMessage.append(comment.getText()).append("\n");
            }
            String testFailed = ServiceMessageBuilder.testFailed(testName)
                    .addAttribute("error", "true")
                    .addAttribute("message", errorMessage.toString())
                                                  .toString();
            handleMessageSend(testFailed);
        }
        if (hasSubtests)
            handleMessageSend(ServiceMessageBuilder.testSuiteFinished(testName).toString());
        else
            handleMessageSend(ServiceMessageBuilder.testFinished(testName).toString());
    }

    private void handleMessageSend(String message) throws ParseException {
        super.processServiceMessages(message, ProcessOutputTypes.STDOUT, myVisitor);
    }
}
