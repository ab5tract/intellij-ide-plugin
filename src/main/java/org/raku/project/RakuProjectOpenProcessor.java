package org.raku.project;

import com.intellij.ide.impl.OpenProjectTask;
import com.intellij.ide.impl.ProjectUtilCore;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ex.ProjectManagerEx;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.projectImport.ProjectOpenProcessor;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;

@InternalIgnoreDependencyViolation
public class RakuProjectOpenProcessor extends ProjectOpenProcessor {
    @Override
    public @NotNull @Nls String getName() {
        return "Raku";
    }

    @Override
    public boolean canOpenProject(@NotNull VirtualFile file) {
        if (file.isDirectory()) {
            if (file.toNioPath().resolve("META6.json").toFile().exists()) {
                return true;
            }
            if (file.toNioPath().resolve("META.info").toFile().exists()) {
                return true;
            }
        }
        String fileName = file.getName();
        return fileName.equals("META6.json") || fileName.equals("META.info");
    }

    @Override
    public @Nullable Project doOpenProject(@NotNull VirtualFile projectFile,
                                           @Nullable Project projectToClose,
                                           boolean forceOpenInNewFrame) {
        VirtualFile projectDirectory = projectFile.isDirectory() ? projectFile : projectFile.getParent();
        Path nioPath = projectDirectory.toNioPath();

        boolean isValidIdeaProject = ProjectUtilCore.isValidProjectPath(projectDirectory.toNioPath());
        OpenProjectTask options = new OpenProjectTask(
          true, projectToClose, !isValidIdeaProject, isValidIdeaProject);
        Project project = ProjectManagerEx.getInstanceEx().openProject(nioPath, options);
        if (project != null)
            importProjectAfterwards(project, projectFile);
        return project;
    }

    @Override
    public void importProjectAfterwards(@NotNull Project project, @NotNull VirtualFile file) {
        RakuProjectBuilder projectBuilder = new RakuProjectBuilder(null);
        projectBuilder.setFileToImport(file.toString());
        projectBuilder.commit(project);
    }

    @Override
    public boolean canImportProjectAfterwards() {
        return true;
    }

    @Override
    public boolean isStrongProjectInfoHolder() {
        return true;
    }
}
