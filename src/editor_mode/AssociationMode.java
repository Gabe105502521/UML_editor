package editor_mode;

import editor_main.Panel;
import editor_shape.AssociationLine;

import java.awt.event.MouseEvent;

public class AssociationMode extends BaseLineMode {


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        if (checkWhetherAdd(endPoint)) {
            Panel.getSLineList().add(new AssociationLine(startPort, endPort));
        }
    }
}
