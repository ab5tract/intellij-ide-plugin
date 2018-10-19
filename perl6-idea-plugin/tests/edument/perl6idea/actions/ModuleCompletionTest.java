package edument.perl6idea.actions;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.impl.file.PsiDirectoryImpl;
import com.intellij.testFramework.LightProjectDescriptor;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;
import edument.perl6idea.Perl6LightProjectDescriptor;

import java.io.IOException;
import java.nio.file.Paths;

public class ModuleCompletionTest extends LightPlatformCodeInsightFixtureTestCase {
    @Override
    protected LightProjectDescriptor getProjectDescriptor() {
        return new Perl6LightProjectDescriptor();
    }

    public void testNewModuleProperties() {
        PsiManagerImpl psiManager = (PsiManagerImpl)PsiManager.getInstance(getProject());
        VirtualFile baseDir = getProject().getBaseDir();
        NewModuleAction newModuleAction = new NewModuleAction();
        newModuleAction.setBaseDir(baseDir.getCanonicalPath());

        // Test project dir case
        PsiDirectoryImpl psiDirectory = new PsiDirectoryImpl(psiManager, baseDir);
        assertNull(newModuleAction.processNavigatable(psiDirectory));
        assertEquals(newModuleAction.getBaseDir(), baseDir.getCanonicalPath());

        // Test 'lib' case
        newModuleAction.setBaseDir(Paths.get(baseDir.getCanonicalPath(), "lib").toString());
        VirtualFile lib = baseDir.findFileByRelativePath("lib");
        psiDirectory = new PsiDirectoryImpl(psiManager, lib);
        assertNull(newModuleAction.processNavigatable(psiDirectory));
        assertEquals(newModuleAction.getBaseDir(), lib.getCanonicalPath());

        // Test 'lib/Foo' case
        VirtualFile foo = getOrCreateDirectory(lib, "Foo");
        PsiDirectory insideLib = new PsiDirectoryImpl(psiManager, foo);
        assertEquals("Foo::", newModuleAction.processNavigatable(insideLib));
        assertEquals(newModuleAction.getBaseDir(), Paths.get(baseDir.getCanonicalPath(), "lib").toString());

        // Test 't' case
        VirtualFile t = getOrCreateDirectory(baseDir, "t");
        PsiDirectory insideTests = new PsiDirectoryImpl(psiManager, t);
        assertEmpty(newModuleAction.processNavigatable(insideTests));
        assertTrue(newModuleAction.getBaseDir().equals(Paths.get(baseDir.getCanonicalPath(), "t").toString()));
        removeLeftovers(foo, t);
    }

    private void removeLeftovers(VirtualFile... files) {
        ApplicationManager.getApplication().runWriteAction(() -> {
            try {
                for (VirtualFile file : files) {
                    file.delete(this);
                }
            }
            catch (IOException ignore) {
            }
        });
    }

    VirtualFile getOrCreateDirectory(VirtualFile base, String name) {
        if (base.findFileByRelativePath(name) == null) {
            ApplicationManager.getApplication().runWriteAction(() -> {
                try {
                    base.createChildDirectory(this, name);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return base.findFileByRelativePath(name);
    }
}
