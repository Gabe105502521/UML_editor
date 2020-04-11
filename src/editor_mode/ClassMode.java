package editor_mode;

import editor_main.Panel;
import editor_shape.ClassObj;

import java.awt.event.MouseEvent;

public class ClassMode extends BaseObjMode {

    @Override
    public void mousePressed(MouseEvent e) {
        Panel.getShapeList().add(new ClassObj(e.getPoint()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
