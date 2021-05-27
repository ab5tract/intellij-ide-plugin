package edument.perl6idea.psi;

import edument.perl6idea.pod.PodDomNode;

import java.util.HashMap;
import java.util.Map;

public class PodRenderingContext {
    private final Map<String, PodDomNode> semanticBlocks = new HashMap<>();

    public void addSemanticBlock(String typename, PodDomNode domNode) {
        semanticBlocks.put(typename, domNode);
    }

    public Map<String, PodDomNode> getSemanticBlocks() {
        return semanticBlocks;
    }
}
