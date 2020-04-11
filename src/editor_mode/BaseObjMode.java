package editor_mode;

import editor_main.Panel;
import editor_shape.BaseObj;
import editor_shape.Port;

import java.awt.*;
import java.awt.event.MouseEvent;
public  class BaseObjMode {
    protected Port startPort, endPort;
    protected Point startPoint, endPoint;

    public void mousePressed(MouseEvent e){

    };

    public void mouseReleased(MouseEvent e){
    };

    public void mouseClicked(MouseEvent e){

    };

    public int checkInShape(Point p) {
        for (int i = 0; i < Panel.getShapeList().size(); i++) {
            if (Panel.getShapeList().get(i).inside(p)) {
                return i;
            }
        }
        return -1;
    }

    public static void removeOldPort() {
        Panel.getShapeList().removeIf(s ->{
            boolean condition = s.getClass().equals(Port.class);
            return condition;
        });
    }
    //this way will have concurrentmodificationexception
    /*for (Shape s: Panel.getShapeList()) {
          if (s.getClass().equals(Port.class)) {
              Panel.getShapeList().removeIf(s);
          }
      }*/
}
