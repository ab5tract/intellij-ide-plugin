package edument.perl6idea.coverage;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ex.EditorGutterComponentEx;
import com.intellij.openapi.editor.markup.ActiveGutterRenderer;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.ui.ColorUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Perl6CoverageLineMarkerRenderer implements ActiveGutterRenderer {
    private final TextAttributes style;

    public Perl6CoverageLineMarkerRenderer(TextAttributes style) {
        this.style = style;
    }

    @Override
    public void doAction(Editor editor, MouseEvent e) {
    }

    @Override
    public boolean canDoAction(MouseEvent e) {
        return false;
    }

    @Override
    public void paint(Editor editor, Graphics g, Rectangle r) {
        Color bgColor = style.getBackgroundColor();
        if (bgColor == null) {
            bgColor = style.getForegroundColor();
        }
        if (editor.getSettings().isLineNumbersShown() || editor.getGutter().isAnnotationsShown()) {
            if (bgColor != null) {
                bgColor = ColorUtil.toAlpha(bgColor, 150);
            }
        }
        if (bgColor != null) {
            g.setColor(bgColor);
        }
        g.fillRect(r.x, r.y, r.width, r.height);
    }
}
