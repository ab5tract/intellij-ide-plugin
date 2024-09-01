package edument.rakuidea.utils;

import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import edument.rakuidea.event.ModuleMetaChangeListener;
import edument.rakuidea.metadata.RakuMetaDataComponent;
import org.jetbrains.annotations.NotNull;

@InternalIgnoreDependencyViolation
public class RakuModuleListStarter implements StartupActivity.Background {
    @Override
    public void runActivity(@NotNull Project project) {
        // Create service if not yet
        project.getService(RakuModuleListFetcher.class);
        RakuModuleListFetcher.refreshModules(project);
        // Initialize metadata listeners
        Module[] modules = ModuleManager.getInstance(project).getModules();
        for (Module module : modules) {
            module.getService(RakuMetaDataComponent.class);
            module.getService(ModuleMetaChangeListener.class);
        }
    }
}
