package edument.rakuidea.psi;

import com.intellij.openapi.util.TextRange;
import edument.rakuidea.pod.PodDomNode;

public interface PodFormatted extends PodElement {
    String getFormatCode();
    TextRange getFormattedTextRange();
    PodDomNode buildPodDom();
}
