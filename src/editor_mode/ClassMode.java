package editor_mode;

import editor_main.Panel;
import editor_shape.ClassObj;
import editor_shape.Shape;

import java.awt.event.MouseEvent;

public class ClassMode extends BaseObjMode {

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Shape tmp = new ClassObj(e.getPoint());
        tmp.checkOverlap();
        Panel.getShapeList().add(tmp);
    }
}
