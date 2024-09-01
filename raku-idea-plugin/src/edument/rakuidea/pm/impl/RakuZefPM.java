package edument.rakuidea.pm.impl;

import com.intellij.execution.ExecutionException;
 import com.intellij.openapi.project.Project;
import edument.rakuidea.pm.RakuPackageManager;
import edument.rakuidea.pm.RakuPackageManagerKind;
import edument.rakuidea.pm.ui.RakuPMWidget;
import edument.rakuidea.utils.RakuCommandLine;

import java.util.HashSet;
import java.util.Set;

public class RakuZefPM extends RakuPackageManager {
    public RakuZefPM(String location) {
        super(location);
    }

    @Override
    public RakuPackageManagerKind getKind() {
        return RakuPackageManagerKind.ZEF;
    }

    @Override
    public void install(Project project, String spec) throws ExecutionException {
        RakuCommandLine cmd = new RakuCommandLine(project);
        cmd.addParameter(location);
        cmd.addParameter("install");
        cmd.addParameter(spec);
        RakuPMWidget.initAndRun(project, cmd);
    }

    @Override
    public Set<String> getInstalledDistributions(Project project) throws ExecutionException {
        RakuCommandLine cmd = new RakuCommandLine(project);
        cmd.addParameter(location);
        cmd.addParameter("list");
        cmd.addParameter("--installed");
        return new HashSet<>(cmd.executeAndRead(null));
    }
}
