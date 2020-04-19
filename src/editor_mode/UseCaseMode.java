package editor_mode;

import editor_main.Panel;
import editor_shape.Shape;
import editor_shape.UseCaseObj;

import java.awt.event.MouseEvent;

public class UseCaseMode extends BaseObjMode {

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Shape tmp = new UseCaseObj(e.getPoint());
        tmp.checkOverlap();
        Panel.getShapeList().add(tmp);
    }
}
