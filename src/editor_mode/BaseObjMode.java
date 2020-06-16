package editor_mode;

import editor_shape.Shape;
import editor_main.Canvas;
import java.awt.*;
import java.awt.event.MouseEvent;

public  class BaseObjMode {
    protected Point startPoint, endPoint;
    protected Canvas canvas = Canvas.getInstance();
    public void mousePressed(MouseEvent e){

    };

    public void mouseReleased(MouseEvent e){
    };

    public void mouseClicked(MouseEvent e){

    };

    public int checkInShape(Point p) {
        //因為有照深度，要反著
        for (int i = canvas.getShapeList().size() - 1; i >= 0 ; i--) {
            if (canvas.getShapeList().get(i).inside(p)) {
                return i;
            }
        }
        return -1;
    }

    public void removeOldPort() {
        for (Shape s: canvas.getShapeSelected()) {
            s.setSelected(false);
        }
        canvas.getShapeSelected().clear();
    }
}
