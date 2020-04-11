package editor_mode;

import editor_main.Panel;
import editor_shape.AssociationLine;
import editor_shape.BaseObj;
import java.awt.event.MouseEvent;

public class AssociationMode extends BaseLineMode {


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        int tmp = checkInShape(endPoint);
        if (tmp != -1 && startShape != null) {
            endShape = (BaseObj) Panel.getShapeList().get(tmp);
            if (startShape != endShape) {
                endPort = endShape.findNearestPort(endPoint);
                Panel.getShapeList().add(new AssociationLine(startPort, endPort));
            }
        } else  {
        }
        startShape = null;
    }
}
