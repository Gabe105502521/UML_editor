package editor_mode;

import editor_main.Panel;
import editor_shape.GroupObj;
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
                return i;
            }
        }
        return -1;
    }

    public static void removeOldPort() {
        for (int i = 0; i < Panel.getShapeList().size(); i++) {
            Shape tmp = Panel.getShapeList().get(i);
            if (tmp instanceof Port) {
                Panel.getShapeList().remove(tmp);
                i--; //手動維護，避免漏掉數據
            }
        }
    }

    //為了用到的都回傳統一的值
    //public boolean isInGroup(Shape s) {
    public GroupObj isInGroup(Shape s) {
        GroupObj g1 = null;
        for (GroupObj g: Panel.getGroupObjList()) {
            if (g.isContain(s)) {
                return g;
            }
        }
        return new GroupObj();
    }
    //this way will have concurrentmodificationexception
    /*for (Shape s: Panel.getShapeList()) {
          if (s.getClass().equals(Port.class)) {
              Panel.getShapeList().removeIf(s);
          }
      }*/


}
