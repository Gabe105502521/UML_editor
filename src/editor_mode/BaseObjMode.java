package editor_mode;

import editor_main.Panel;
import editor_shape.Port;
import editor_shape.Shape;

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
        //因為有照深度，要反著
        for (int i = Panel.getShapeList().size() - 1; i >= 0 ; i--) {
            if (Panel.getShapeList().get(i).inside(p)) {
                System.out.println(Panel.getShapeList().get(i).getDepth());
                return i;
            }
        }
        /*
        for (int i = 0 ; i < Panel.getShapeList().size() ; i++) {
            if (Panel.getShapeList().get(i).inside(p)) {
                return i;
            }
        }*/
        return -1;
    }

    public static void removeOldPort() {
        /*
        Panel.getShapeList().removeIf(s ->{
            boolean condition = s.getClass().equals(Port.class);
            return condition;
        });*/
        for (int i = 0; i < Panel.getShapeList().size(); i++) {
            Shape tmp = Panel.getShapeList().get(i);
            if (tmp instanceof Port) {
                Panel.getShapeList().remove(tmp);
                i--; //手動維護，避免漏掉數據
            }
        }
    }
    //this way will have concurrentmodificationexception
    /*for (Shape s: Panel.getShapeList()) {
          if (s.getClass().equals(Port.class)) {
              Panel.getShapeList().removeIf(s);
          }
      }*/


}
