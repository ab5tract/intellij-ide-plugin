package edument.rakuidea.surrountWith.descriptors;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import edument.rakuidea.psi.RakuElementFactory;

public class Perl6RegexPositionalSurrounder implements Perl6RegexSurrounder {
    @Override
    public String getTemplateDescription() {
        return "( ) (Regex)";
    }

    @Override
    public PsiElement createAtom(Project project) {
        return RakuElementFactory.createRegexGroup(project, true);
    }
}

