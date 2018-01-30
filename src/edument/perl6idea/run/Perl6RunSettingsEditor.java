package edument.perl6idea.run;

import com.intellij.execution.ui.CommonProgramParametersPanel;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.ui.components.JBTabbedPane;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class Perl6RunSettingsEditor extends SettingsEditor<Perl6RunConfiguration> {
    private Project myProject;
    private TextFieldWithBrowseButton fileField;
    private CommonProgramParametersPanel myParams;
    private JTextField myDebugPort;
    private JCheckBox toStartSuspended;
    private RawCommandLineEditor myPerl6ParametersPanel;

    Perl6RunSettingsEditor(Project project) {
        super();
        myProject = project;
    }

    @Override
    protected void resetEditorFrom(@NotNull Perl6RunConfiguration conf) {
        fileField.setText(conf.getScriptPath());
        if (conf.getDebugPort() == 0) {
            myDebugPort.setText(String.valueOf(9999));
        } else {
            myDebugPort.setText(String.valueOf(conf.getDebugPort()));
        }
        toStartSuspended.setSelected(conf.isStartSuspended());
        if (conf.getInterpreterParameters() == null) {
            myPerl6ParametersPanel.setText("");
        }
        myParams.reset(conf);
        if (conf.getWorkingDirectory() == null) {
            myParams.setWorkingDirectory(myProject.getBasePath());
        }
    }

    @Override
    protected void applyEditorTo(@NotNull Perl6RunConfiguration conf) throws ConfigurationException {
        String fileLine = fileField.getText();
        VirtualFile file = LocalFileSystem.getInstance().findFileByPath(fileLine);
        if (file == null || !file.exists()) {
            throw new ConfigurationException("Main script path is incorrect");
        } else if (Objects.equals(fileLine, "")) {
            throw new ConfigurationException("Main script path is absent");
        } else  {
            conf.setScriptPath(fileLine);
        }
        conf.setDebugPort(Integer.parseInt(myDebugPort.getText()));
        conf.setStartSuspended(toStartSuspended.isSelected());
        conf.setInterpreterParameters(myPerl6ParametersPanel.getText());
        myParams.applyTo(conf);
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        JComponent mainTab = getMainTab();
        JComponent debugTab = getDebugTab();
        if (mainTab != null && debugTab != null) {
            JBTabbedPane tabbedPaneWrapper = new JBTabbedPane();
            tabbedPaneWrapper.addTab("Main", mainTab);
            tabbedPaneWrapper.addTab("Debug", debugTab);
            return tabbedPaneWrapper;
        } else if (mainTab != null) {
            return mainTab;
        } else if (debugTab != null) {
            return debugTab;
        }
        throw new RuntimeException("No components created for settings editor");
    }

    private JComponent getMainTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new VerticalFlowLayout(VerticalFlowLayout.TOP, 0, 5, true, false));
        FileChooserDescriptor chooserDescriptor = new FileChooserDescriptor(
                true, false,
                false, false,
                false, false) {
            @Override
            public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
                return file.isDirectory() || file.getExtension() == null
                        || Arrays.asList("pm6", "pl6", "p6", "").contains(file.getExtension());
            }
        };
        fileField = new TextFieldWithBrowseButton();
        fileField.addBrowseFolderListener("Select Script", null, myProject,
                chooserDescriptor);
        myParams = new CommonProgramParametersPanel() {
            @Override
            protected void addComponents() {
                LabeledComponent<?> file = LabeledComponent.create(fileField, "Script");
                file.setLabelLocation(BorderLayout.WEST);
                add(file);
                super.addComponents();
                myPerl6ParametersPanel = new RawCommandLineEditor();
                LabeledComponent<RawCommandLineEditor> perl6ParametersPanel =
                        LabeledComponent.create(myPerl6ParametersPanel, "Perl 6 parameters");
                perl6ParametersPanel.setLabelLocation(BorderLayout.WEST);

                add(perl6ParametersPanel);
            }
        };
        myParams.setProgramParametersLabel("Script parameters:");
        myParams.setWorkingDirectory(myProject.getBasePath());
        return myParams;
    }

    private JComponent getDebugTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new VerticalFlowLayout(VerticalFlowLayout.TOP, 0, 5, true, false));
        myDebugPort = new JTextField(String.valueOf(9999)) {
            @Override
            protected Document createDefaultModel() {
                return new PlainDocument() {
                    @Override
                    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                        if (str == null) return;
                        String oldString = getText(0, getLength());
                        String newString = oldString.substring(0, offs) + str + oldString.substring(offs);
                        try {
                            int newValue = Integer.parseInt(newString);
                            if (newValue < 65536) {
                                super.insertString(offs, str, a);
                            }
                        } catch (NumberFormatException e) {
                        }
                    }
                };
            }
        };
        LabeledComponent<JTextField> debugPort = LabeledComponent.create(myDebugPort, "Debug port");
        debugPort.setLabelLocation(BorderLayout.WEST);
        panel.add(debugPort);
        toStartSuspended = new JCheckBox("Start suspended");
        panel.add(toStartSuspended);
        return panel;
    }
}
