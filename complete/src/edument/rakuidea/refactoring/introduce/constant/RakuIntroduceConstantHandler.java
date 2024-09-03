package edument.rakuidea.refactoring.introduce.constant;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import edument.rakuidea.psi.RakuElementFactory;
import edument.rakuidea.refactoring.introduce.IntroduceHandler;
import edument.rakuidea.refactoring.introduce.IntroduceOperation;
import edument.rakuidea.refactoring.introduce.IntroduceValidator;

public class RakuIntroduceConstantHandler extends IntroduceHandler {
    public RakuIntroduceConstantHandler(IntroduceValidator validator, String dialogTitle) {
        super(validator, dialogTitle);
    }

    @Override
    protected String getHelpId() {
        return  "refactoring.raku.introduce.constant";
    }

    @Override
    protected String getRefactoringId() {
        return "refactoring.introduceConstant";
    }

    @Override
    protected PsiElement createDeclaration(IntroduceOperation operation) {
        Project project = operation.getProject();
        PsiElement initializer = operation.getInitializer();
        return RakuElementFactory.createConstantAssignment(project, operation.getName(), initializer.getText());
    }
}
