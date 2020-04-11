package editor_mode;

import editor_main.Panel;
import editor_shape.UseCaseObj;

import java.awt.event.MouseEvent;

public class UseCaseMode extends BaseObjMode {

    @Override
    public void mousePressed(MouseEvent e) {
        Panel.getShapeList().add(new UseCaseObj(e.getPoint()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
