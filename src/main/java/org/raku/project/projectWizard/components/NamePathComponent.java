// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.raku.project.projectWizard.components;

import com.intellij.ide.IdeBundle;
import com.intellij.ide.highlighter.ProjectFileType;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.ide.util.BrowseFilesListener;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.FieldPanel;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.StartupUiUtil;
import org.raku.utils.CommaProjectWizardUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Paths;

import static java.awt.GridBagConstraints.*;

/**
 * @author Eugene Zhuravlev
 */
public class NamePathComponent extends JPanel {
  private static final Logger LOG = Logger.getInstance(NamePathComponent.class);

  private final JTextField myTfName;
  private final JTextField myTfPath;
  private final JLabel myNameLabel;
  private final FieldPanel myPathPanel;

  private boolean myIsNameChangedByUser = false;
  private boolean myIsPathChangedByUser = false;
  private boolean myIsPathNameSyncEnabled = true;
  private boolean myIsNamePathSyncEnabled = true;
  private boolean myShouldBeAbsolute;

  public NamePathComponent(String nameLabelText, String pathLabelText, String pathChooserTitle, String pathChooserDescription) {
    this(nameLabelText, pathLabelText, pathChooserTitle, pathChooserDescription, true);
  }

  public NamePathComponent(String nameLabelText,
                           String pathLabelText,
                           String pathChooserTitle,
                           String pathChooserDescription,
                           boolean hideIgnored) {
    this(nameLabelText, pathLabelText, pathChooserTitle, pathChooserDescription, hideIgnored, true);
  }

  public NamePathComponent(String nameLabelText,
                           String pathLabelText,
                           String pathChooserTitle,
                           String pathChooserDescription,
                           boolean hideIgnored,
                           boolean bold) {
    super(new GridBagLayout());

    myTfName = new JTextField();
    myTfName.setDocument(new NameFieldDocument());
    myTfName.setPreferredSize(new Dimension(200, myTfName.getPreferredSize().height));

    myTfPath = new JTextField();
    myTfPath.setDocument(new PathFieldDocument());
    myTfPath.setPreferredSize(new Dimension(200, myTfPath.getPreferredSize().height));

    myNameLabel = new JLabel(nameLabelText);
    if (bold) myNameLabel.setFont(StartupUiUtil.getLabelFont().deriveFont(Font.BOLD));
    myNameLabel.setLabelFor(myTfName);

    FileChooserDescriptor chooserDescriptor = (FileChooserDescriptor)BrowseFilesListener.SINGLE_DIRECTORY_DESCRIPTOR.clone();
    chooserDescriptor.setHideIgnored(hideIgnored);
    BrowseFilesListener browseButtonActionListener = new BrowseFilesListener(myTfPath, pathChooserTitle, pathChooserDescription, chooserDescriptor) {
      @Override
      public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        myIsPathChangedByUser = true;
      }
    };
    myPathPanel = new FieldPanel(myTfPath, null, null, browseButtonActionListener, null);

    JLabel pathLabel = new JLabel(pathLabelText);
    if (bold) pathLabel.setFont(StartupUiUtil.getLabelFont().deriveFont(Font.BOLD));
    pathLabel.setLabelFor(myTfPath);

    add(myNameLabel, new GridBagConstraints(0, RELATIVE, 1, 1, 0.0, 0.0, WEST, NONE, JBUI.insets(0, 0, 5, 4), 0, 0));
    add(myTfName, new GridBagConstraints(1, RELATIVE, 1, 1, 1.0, 0.0, NORTHWEST, HORIZONTAL, JBUI.insetsBottom(5), 0, 0));
    add(pathLabel, new GridBagConstraints(0, RELATIVE, 1, 1, 0.0, 0.0, WEST, NONE, JBUI.insets(0, 0, 5, 4), 0, 0));
    add(myPathPanel, new GridBagConstraints(1, RELATIVE, 1, 1, 1.0, 0.0, NORTHWEST, HORIZONTAL, JBUI.insetsBottom(5), 0, 0));
  }

  public static NamePathComponent initNamePathComponent(WizardContext context) {
    NamePathComponent component = new NamePathComponent(
      IdeBundle.message("label.project.name"),
      IdeBundle.message("label.project.files.location"),
      "Select project",
      "Select path to project file",
      true, false
    );
    String baseDir = context.getProjectFileDirectory();
    String projectName = context.getProjectName();
    String initialProjectName = projectName != null ? projectName : CommaProjectWizardUtil.findNonExistingFileName(baseDir, "untitled", "");
    component.setPath(projectName == null ? (baseDir + File.separator + initialProjectName) : baseDir);
    component.setNameValue(initialProjectName);
    component.getNameComponent().select(0, initialProjectName.length());
    return component;
  }

  public boolean validateNameAndPath(WizardContext context, boolean defaultFormat) throws ConfigurationException {
    String name = getNameValue();
    if (StringUtil.isEmptyOrSpaces(name)) {
      throw new ConfigurationException("Enter a file name");
    }

    String projectDirectoryPath = getPath();
    if (StringUtil.isEmptyOrSpaces(projectDirectoryPath)) {
      ApplicationNamesInfo info = ApplicationNamesInfo.getInstance();
      throw new ConfigurationException(
          String.format("Enter a file name to create a new %s %s", info.getFullProductName(), context.getPresentationName()));
    }
    if (myShouldBeAbsolute && !new File(projectDirectoryPath).isAbsolute()) {
      throw new ConfigurationException(StringUtil.capitalize(
          String.format("Enter %s file location", context.getPresentationName())));
    }

    boolean shouldPromptCreation = isPathChangedByUser();
    String message = String.format("The %s directory\n", context.getPresentationName());
    if (!CommaProjectWizardUtil.createDirectoryIfNotExists(message, projectDirectoryPath, shouldPromptCreation)) {
      return false;
    }

    File projectDirectory = new File(projectDirectoryPath);
    if (projectDirectory.exists() && !projectDirectory.canWrite()) {
      throw new ConfigurationException(String.format("Directory %s  doesn't seem to be writeable. Please choose another location.", projectDirectoryPath));
    }
    for (Project p : ProjectManager.getInstance().getOpenProjects()) {
      if (ProjectUtil.isSameProject(Paths.get(projectDirectoryPath), p)) {
        throw new ConfigurationException(String.format("Directory %s is already taken by the project %s. Please choose another location.", projectDirectoryPath, p.getName()));
      }
    }
    if (projectDirectory.exists() && !projectDirectory.isDirectory()) {
      LOG.warn(String.format("Project '%s' directory should be a directory", projectDirectoryPath));
    }
    boolean shouldContinue = true;
    if (!ApplicationManager.getApplication().isUnitTestMode()) {
      String fileName = defaultFormat ? name + ProjectFileType.DOT_DEFAULT_EXTENSION : Project.DIRECTORY_STORE_FOLDER;
      File projectFile = new File(projectDirectory, fileName);
      if (projectFile.exists()) {
        message = "File already exists";
        int answer = Messages.showYesNoDialog(message, IdeBundle.message("title.file.already.exists"), Messages.getQuestionIcon());
        shouldContinue = (answer == Messages.YES);
      }
    }
    return shouldContinue;
  }

  public String getNameValue() {
    return myTfName.getText().trim();
  }

  public void setNameValue(String name) {
    boolean isNameChangedByUser = myIsNameChangedByUser;
    myIsNamePathSyncEnabled = false;
    try {
      myTfName.setText(name);
    }
    finally {
      myIsNameChangedByUser = isNameChangedByUser;
      myIsNamePathSyncEnabled = true;
    }
  }

  public String getPath() {
    String text = myTfPath.getText().trim();
    return FileUtil.toCanonicalPath(FileUtil.expandUserHome(text));
  }

  public void setPath(String path) {
    boolean isPathChangedByUser = myIsPathChangedByUser;
    myIsPathNameSyncEnabled = false;
    try {
      myTfPath.setText(FileUtil.getLocationRelativeToUserHome(FileUtil.toSystemDependentName(path)));
    }
    finally {
      myIsPathChangedByUser = isPathChangedByUser;
      myIsPathNameSyncEnabled = true;
    }
  }

  @NotNull
  public JTextField getNameComponent() {
    return myTfName;
  }

  public void setNameComponentVisible(boolean visible) {
    myTfName.setVisible(visible);
    myNameLabel.setVisible(visible);
  }

  @NotNull
  public JTextField getPathComponent() {
    return myTfPath;
  }

  @NotNull
  public FieldPanel getPathPanel() {
    return myPathPanel;
  }

  public boolean isNameChangedByUser() {
    return myIsNameChangedByUser;
  }

  public boolean isPathChangedByUser() {
    return myIsPathChangedByUser;
  }

  public void addChangeListener(Runnable callback) {
    DocumentAdapter adapter = new DocumentAdapter() {
      @Override
      protected void textChanged(@NotNull DocumentEvent e) {
        callback.run();
      }
    };
    myTfName.getDocument().addDocumentListener(adapter);
    myTfPath.getDocument().addDocumentListener(adapter);
  }

  public void setShouldBeAbsolute(boolean shouldBeAbsolute) {
    myShouldBeAbsolute = shouldBeAbsolute;
  }

  private class NameFieldDocument extends PlainDocument {
    NameFieldDocument() {
      addDocumentListener(new DocumentAdapter() {
        @Override
        public void textChanged(@NotNull DocumentEvent event) {
          myIsNameChangedByUser = true;
          syncNameAndPath();
        }
      });
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
      boolean ok = true;
      for (int idx = 0; idx < str.length() && ok; idx++) {
        char ch = str.charAt(idx);
        ok = ch != File.separatorChar && ch != '\\' && ch != '/' && ch != '|' && ch != ':';
      }
      if (ok) {
        super.insertString(offs, str, a);
      }
    }

    private void syncNameAndPath() {
      if (myIsNamePathSyncEnabled && !isPathChangedByUser()) {
        try {
          myIsPathNameSyncEnabled = false;
          String name = getText(0, getLength());
          String path = myTfPath.getText().trim();
          int lastSeparatorIndex = path.lastIndexOf(File.separator);
          if (lastSeparatorIndex >= 0) {
            setPath(path.substring(0, lastSeparatorIndex + 1) + name);
          }
          else if (!path.isEmpty()) {
            setPath(path + File.separatorChar + name);
          }
        }
        catch (BadLocationException e) {
          LOG.error(e);
        }
        finally {
          myIsPathNameSyncEnabled = true;
        }
      }
    }
  }

  private class PathFieldDocument extends PlainDocument {
    PathFieldDocument() {
      addDocumentListener(new DocumentAdapter() {
        @Override
        public void textChanged(@NotNull DocumentEvent event) {
          myIsPathChangedByUser = true;
          syncPathAndName();
        }
      });
    }

    private void syncPathAndName() {
      if (myIsPathNameSyncEnabled && !isNameChangedByUser()) {
        try {
          myIsNamePathSyncEnabled = false;
          String path = getText(0, getLength());
          int lastSeparatorIndex = path.lastIndexOf(File.separator);
          if (lastSeparatorIndex >= 0 && (lastSeparatorIndex + 1) < path.length()) {
            setNameValue(path.substring(lastSeparatorIndex + 1));
          }
        }
        catch (BadLocationException e) {
          LOG.error(e);
        }
        finally {
          myIsNamePathSyncEnabled = true;
        }
      }
    }
  }
}