package org.raku.pod;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.raku.psi.RakuColonPair;
import org.raku.psi.RakuTrait;
import org.raku.utils.RakuUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* The base of all nodes that indicate documentable program elements. */
public abstract class PodDomDeclarator extends PodDomNode {
    // The set of things that the element is exported as, if any.
    private final RakuTrait exportTrait;

    // The element's short name, which it would be exported as.
    private final String shortName;

    // The global name, if it has one.
    private final String globalName;

    // The Pod documentation comments.
    private final List<PsiElement> docComments;

    public PodDomDeclarator(int offset, @NotNull String shortName, @Nullable String globalName,
                List<PsiElement> docComments, RakuTrait exportTrait) {
        super(offset);
        this.shortName = shortName;
        this.globalName = globalName;
        this.docComments = docComments;
        this.exportTrait = exportTrait;
    }

    protected String getPrimaryName() {
        return globalName != null ? globalName : shortName;
    }

    protected void renderDocComments(StringBuilder builder, PodRenderingContext context) {
        boolean inParagraph = false;
        int seenNewLines = 0;
        for (PsiElement comment : docComments) {
            // Process empty lines, which may be paragraph breaks.
            if (comment == null) {
                seenNewLines++;
                continue;
            }

            // Add paragraph break if needed.
            if (seenNewLines > 1 && inParagraph) {
                builder.append("</p>");
                inParagraph = false;
            }
            else {
                builder.append(' ');
            }
            seenNewLines = 0;

            // If it's not empty, emit the content, starting a paragraph if
            // needed.
            if (!inParagraph) {
                builder.append("<p>");
                inParagraph = true;
            }
            builder.append(RakuUtils.escapeHTML(comment.getText()));
        }
        if (inParagraph)
            builder.append("</p>");
    }

    protected void renderExportTags(StringBuilder builder, PodRenderingContext context) {
        if (exportTrait == null)
            return;

        List<String> tags = new ArrayList<>();
        Collection<RakuColonPair> declaredTags = PsiTreeUtil.findChildrenOfType(exportTrait, RakuColonPair.class);
        if (declaredTags.isEmpty()) {
            tags.add("DEFAULT");
        }
        else {
            for (RakuColonPair tag : declaredTags) {
                String key = tag.getKey();
                if (key != null && !key.isEmpty())
                    tags.add(key);
            }
        }

        for (String tag : tags) {
            builder.append(" <span class=\"doc-export-tag\">");
            builder.append(RakuUtils.escapeHTML(tag));
            builder.append("</span>");
        }
    }
}
