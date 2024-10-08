package org.raku.utils;

import com.intellij.CommonBundle;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.io.FileUtil;

import java.io.File;

public class CommaProjectWizardUtil {
    public static String findNonExistingFileName(String searchDirectory, String preferredName, String extension) {
        for (int idx = 0; ; idx++) {
            String fileName = (idx > 0 ? preferredName + idx : preferredName) + extension;
            if (!new File(searchDirectory, fileName).exists()) {
                return fileName;
            }
        }
    }

    public static boolean createDirectoryIfNotExists(String promptPrefix, String directoryPath, boolean promptUser) {
        File dir = new File(directoryPath);

        if (!dir.exists()) {
            if (promptUser) {
                String ide = ApplicationNamesInfo.getInstance().getFullProductName();
                String message = String.format("%s\"%s\"\ndoes not exist. It will be created by %s",
                                               promptPrefix, dir, ide);
                int answer = Messages
                    .showOkCancelDialog(message, "Directory Does Not Exist", "OK", "Cancel",
                                        Messages.getQuestionIcon());
                if (answer != Messages.OK) {
                    return false;
                }
            }

            if (!FileUtil.createDirectory(dir)) {
                Messages
                    .showErrorDialog(IdeBundle.message("error.failed.to.create.directory", dir.getPath()), CommonBundle.getErrorTitle());
                return false;
            }
        }

        if (SystemInfo.isUnix && !dir.canWrite()) {
            Messages.showErrorDialog("Directory " + dir.getPath() + " is read-only", CommonBundle.getErrorTitle());
            return false;
        }

        return true;
    }
}
