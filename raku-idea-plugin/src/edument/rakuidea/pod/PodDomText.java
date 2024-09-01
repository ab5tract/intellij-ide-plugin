package edument.rakuidea.pod;

import edument.rakuidea.utils.RakuUtils;

public class PodDomText extends PodDomNode {
    private final String text;

    public PodDomText(int offset, String text) {
        super(offset);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void renderInto(StringBuilder builder, PodRenderingContext context) {
        builder.append(RakuUtils.escapeHTML(text));
    }
}
