package editor_mode;

import editor_shape.GeneralizationLine;
import java.awt.event.MouseEvent;

public class GeneralizationMode extends BaseLineMode {

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        if (checkWhetherAdd(endPoint)) {
            canvas.getSLineList().add(new GeneralizationLine(startPort, endPort));
        }
    }
}
