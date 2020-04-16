package editor_mode;

import editor_main.Panel;
import editor_shape.ClassObj;

import java.awt.event.MouseEvent;

public class ClassMode extends BaseObjMode {

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Panel.getShapeList().add(0, new ClassObj(e.getPoint()));
    }
}
