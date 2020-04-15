package editor_mode;

import editor_main.Panel;
import editor_shape.BaseObj;
import editor_shape.GeneralizationLine;
import editor_shape.GroupObj;

import java.awt.event.MouseEvent;

public class GeneralizationMode extends BaseLineMode {
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
            if (startShape != endShape && !endShape.getClass().equals(GroupObj.class)) {
                endPort = endShape.findNearestPort(endPoint);
                Panel.getShapeList().add(new GeneralizationLine(startPort, endPort));
            }
        } else  {
        }
    }
}
