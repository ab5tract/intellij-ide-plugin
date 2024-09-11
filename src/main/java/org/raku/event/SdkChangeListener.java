package org.raku.event;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.InternalIgnoreDependencyViolation;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ProjectExtension;
import org.raku.metadata.RakuMetaDataComponent;
import org.raku.sdk.RakuSdkType;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@InternalIgnoreDependencyViolation
public class SdkChangeListener extends ProjectExtension {
    private final Project myProject;

    public SdkChangeListener(Project project) {
        myProject = project;
    }

    @Override
    public void projectSdkChanged(@Nullable Sdk sdk) {
        if (!ApplicationManager.getApplication().isUnitTestMode()) {
            RakuSdkType.getInstance().invalidateCaches(myProject);
            for (Module module : ModuleManager.getInstance(myProject).getModules()) {
                RakuMetaDataComponent component = module.getService(RakuMetaDataComponent.class);
                if (component != null)
                    component.triggerMetaBuild();
            }
        }
    }

    @Override
    public void readExternal(@NotNull Element element) {
    }

    @Override
    public void writeExternal(@NotNull Element element) {
    }
}
