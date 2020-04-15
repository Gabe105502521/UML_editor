package editor_mode;

import editor_main.Panel;
import editor_shape.BaseObj;
import editor_shape.GroupObj;
import editor_shape.Port;
import java.awt.event.MouseEvent;

public class BaseLineMode extends BaseObjMode {

    protected Port startPort, endPort;
    protected BaseObj startShape, endShape;

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        int tmp = checkInShape(startPoint); //-1就是沒有，反之則是在哪個shape裡
        if (tmp != -1) {
                startShape = (BaseObj) Panel.getShapeList().get(tmp);
            if (!startShape.getClass().equals(GroupObj.class))
                startPort = startShape.findNearestPort(startPoint);
            else startShape = null;
        } else  {
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
