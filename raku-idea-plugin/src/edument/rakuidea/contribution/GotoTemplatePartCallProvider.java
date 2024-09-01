package edument.rakuidea.contribution;

import com.intellij.navigation.GotoRelatedItem;
import com.intellij.navigation.GotoRelatedProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.containers.ContainerUtil;
import edument.rakuidea.cro.CroTemplateIndex;
import edument.rakuidea.cro.template.psi.CroTemplatePart;
import edument.rakuidea.psi.RakuSubCall;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static edument.rakuidea.cro.template.parsing.CroTemplateTokenTypes.PART_NAME;

public class GotoTemplatePartCallProvider extends GotoRelatedProvider {
    @Override
    public @NotNull List<? extends GotoRelatedItem> getItems(@NotNull PsiElement psiElement) {
        if (!(psiElement.getParent() instanceof CroTemplatePart) || psiElement.getNode().getElementType() != PART_NAME)
            return Collections.emptyList();
        String partName = psiElement.getText();

        Collection<RakuSubCall> collection =
            CroTemplateIndex.getInstance().get(partName, psiElement.getProject(), GlobalSearchScope.projectScope(psiElement.getProject()));
        return ContainerUtil.map(collection, s -> new GotoRelatedItem(s));
    }
}
