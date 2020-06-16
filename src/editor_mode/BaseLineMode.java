package editor_mode;

import editor_shape.*;
import editor_shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BaseLineMode extends BaseObjMode {

    protected Port startPort, endPort;
    protected Shape startShape, endShape;

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        int tmp = checkInShape(startPoint); //-1就是沒有，反之則是在哪個shape裡
        if (tmp != -1 ) {
            startShape = canvas.getShapeList().get(tmp);
            if (startShape.getShapesIngroup() == null) { //不是group
                startPort = startShape.findNearestPort(startPoint);
            } else {
                startShape = null;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public boolean checkWhetherAdd(Point endPoint) {
        int tmp = checkInShape(endPoint);
        if (tmp != -1 && startShape != null) {
            endShape = canvas.getShapeList().get(tmp);
            if (endShape.getShapesIngroup() == null && !startShape.equals(endShape)) {
                endPort = endShape.findNearestPort(endPoint);
                return true;
            }
        }
        return false;
    }
}
