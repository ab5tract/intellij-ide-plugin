package edument.perl6idea.utils;

import com.intellij.ide.SaveAndSyncHandler;
import com.intellij.ide.impl.OpenProjectTask;
import com.intellij.ide.impl.ProjectUtil;
import com.intellij.ide.util.projectWizard.ProjectBuilder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ex.ProjectRootManagerEx;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.platform.PlatformProjectOpenProcessor;
import com.intellij.util.ui.UIUtil;
import edument.perl6idea.project.projectWizard.CommaAbstractProjectWizard;
import edument.perl6idea.project.projectWizard.CommaNewProjectWizard;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A simper, Comma-exclusive re-imagination of NewProjectUtil.
 */
public class CommaProjectUtil {
    private final static Logger LOG = Logger.getInstance(CommaProjectUtil.class);

    public static void createNewProject(CommaNewProjectWizard wizard) {
        String title = "Initialization...";
        Runnable warmUp = () -> ProjectManager.getInstance().getDefaultProject();  // warm-up components
        boolean proceed = ProgressManager.getInstance().runProcessWithProgressSynchronously(warmUp, title, true, null);
        if (proceed && wizard.showAndGet()) {
            createFromWizard(wizard);
        }
    }

    public static void createFromWizard(CommaAbstractProjectWizard wizard) {
        try {
            doCreate(wizard, null);
        }
        catch (IOException e) {
            UIUtil.invokeLaterIfNeeded(() -> Messages.showErrorDialog(e.getMessage(), "Project Initialization Failed"));
        }
    }

    private static void doCreate(CommaAbstractProjectWizard wizard, Project projectToClose) throws IOException {
        String projectFilePath = wizard.getNewProjectFilePath();
        for (Project p : ProjectUtil.getOpenProjects()) {
            if (ProjectUtil.isSameProject(Paths.get(projectFilePath), p)) {
                ProjectUtil.focusProjectWindow(p, false);
                return;
            }
        }

        ProjectBuilder projectBuilder = wizard.getProjectBuilder();
        LOG.debug("builder " + projectBuilder);

        ProjectManagerEx projectManager = ProjectManagerEx.getInstanceEx();
        try {
            Path projectFile = Paths.get(projectFilePath);
            Path projectDir;
            if (wizard.getStorageScheme() == StorageScheme.DEFAULT) {
                projectDir = projectFile.getParent();
                if (projectDir == null) {
                    throw new IOException("Cannot create project in '" + projectFilePath + "': no parent file exists");
                }
            }
            else {
                projectDir = projectFile;
            }
            Files.createDirectories(projectDir);

            Project newProject;
            if (projectBuilder == null || !projectBuilder.isUpdate()) {
                String name = wizard.getProjectName();
                if (projectBuilder == null) {
                    newProject = projectManager.newProject(projectFile, OpenProjectTask.newProject().withProjectName(name));
                }
                else {
                    newProject = projectBuilder.createProject(name, projectFilePath);
                }
            }
            else {
                newProject = projectToClose;
            }

            if (newProject == null) {
                return;
            }

            Sdk sdk = wizard.getNewProjectSdk();
            if (sdk != null) {
                CommandProcessor.getInstance().executeCommand(newProject, () -> ApplicationManager.getApplication()
                    .runWriteAction(() -> applySdkToProject(newProject, sdk)), null, null);
            }

            if (projectBuilder != null) {
                // validate can require project on disk
                if (!ApplicationManager.getApplication().isUnitTestMode()) {
                    newProject.save();
                }

                if (!projectBuilder.validate(projectToClose, newProject)) {
                    return;
                }

                projectBuilder.commit(newProject, null, ModulesProvider.EMPTY_MODULES_PROVIDER);
            }

            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                boolean needToOpenProjectStructure = projectBuilder == null || projectBuilder.isOpenProjectSettingsAfter();
                StartupManager.getInstance(newProject).registerPostStartupActivity(() -> {
                    // ensure the dialog is shown after all startup activities are done
                    ApplicationManager.getApplication().invokeLater(() -> {
                        if (needToOpenProjectStructure) {
                            // FIXME re-instantiate Comma modules configurator showing
                            // ModulesConfigurator.showDialog(newProject, null, null);
                        }
                        ApplicationManager.getApplication().invokeLater(() -> {
                            ToolWindow toolWindow = ToolWindowManager.getInstance(newProject).getToolWindow(ToolWindowId.PROJECT_VIEW);
                            if (toolWindow != null) {
                                toolWindow.activate(null);
                            }
                        }, ModalityState.NON_MODAL, newProject.getDisposed());
                    }, ModalityState.NON_MODAL, newProject.getDisposed());
                });
            }

            if (newProject != projectToClose) {
                ProjectUtil.updateLastProjectLocation(projectFile);
                ProjectManagerEx.getInstanceEx().openProject(projectDir, OpenProjectTask.withCreatedProject(newProject).withProjectName(projectFile.getFileName().toString()));
            }

            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                SaveAndSyncHandler.getInstance().scheduleProjectSave(newProject);
            }
        }
        finally {
            if (projectBuilder != null) {
                projectBuilder.cleanup();
            }
        }
    }

    public static void applySdkToProject(@NotNull Project project, @NotNull Sdk jdk) {
        ProjectRootManagerEx rootManager = ProjectRootManagerEx.getInstanceEx(project);
        rootManager.setProjectSdk(jdk);
    }
}