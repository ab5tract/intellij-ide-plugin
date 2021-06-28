package edument.perl6idea.pod;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/* A class-like documentable program element. */
public class PodDomClassyDeclarator extends PodDomDeclarator {
    private final String packageKind;
    private final List<PodDomAttributeDeclarator> attributes = new ArrayList<>();
    private final List<PodDomRoutineDeclarator> methods = new ArrayList<>();

    public PodDomClassyDeclarator(int offset, @NotNull String shortName,
            @Nullable String globalName, List<PsiElement> docComments, @NotNull String packageKind) {
        super(offset, shortName, globalName, docComments);
        this.packageKind = packageKind;
    }

    public void addAttribute(PodDomAttributeDeclarator attribute) {
        attributes.add(attribute);
    }

    public void addMethod(PodDomRoutineDeclarator method) {
        methods.add(method);
    }

    @Override
    public void renderInto(StringBuilder builder, PodRenderingContext context) {
        // Render the name and docs.
        builder.append("<h3 class=\"doc-package-name\">");
        builder.append(getPrimaryName());
        builder.append(" <span class=\"doc-kind\">");
        builder.append(packageKind);
        builder.append("</span></h3>");
        renderDocComments(builder, context);

        // Render any attributes and methods.
        for (PodDomAttributeDeclarator attribute : attributes)
            attribute.renderInto(builder, context);
        for (PodDomRoutineDeclarator method : methods)
            method.renderInto(builder, context);
    }
}
