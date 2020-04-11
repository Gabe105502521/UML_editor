package editor_mode;

import editor_main.Panel;
import editor_main.UMLEditor;
import editor_shape.BaseObj;
import editor_shape.Port;
import editor_shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import static editor_main.Panel.getShapeList;

public class SelectMode extends BaseObjMode {
    private int tmp;
    private List<BaseObj> shapeInRange = new ArrayList<>();
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        tmp = checkInShape(startPoint); // if not, it will be -1
        if (tmp == -1) {
            removeOldPort();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        int difX = endPoint.x - startPoint.x, difY = endPoint.y - startPoint.y;
        //dragging and has shape
        if (endPoint != startPoint && tmp != -1) {
            removeOldPort();
            Panel.getShapeList().get(tmp).adjust(difX, difY);
        } else if (endPoint != startPoint && tmp == -1) {
            System.out.println("?");
            selectShapes(difX, difY);
            for (BaseObj obj:  shapeInRange) {
                for (int i = 0; i < 4; i++) {
                    Panel.getShapeList().add(obj.getPorts()[i]);
                    System.out.println("!");
                }
            }
            shapeInRange.clear();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        tmp = checkInShape(startPoint);
        if (tmp != -1) {
            BaseObj tmpShape = (BaseObj) getShapeList().get(tmp);
            removeOldPort();
            for (int i = 0; i < 4; i++) {
                getShapeList().add(tmpShape.getPorts()[i]);
            }
        }
    }
    public void selectShapes( int difX, int difY) {
        Rectangle bounds = null, shapeRange = null;
        if (difX < 0) {
            if (difY < 0) { //右下->左上
                bounds = new Rectangle(endPoint.x, endPoint.y, -difX, -difY);
            } else { //右上->左下
                bounds = new Rectangle(endPoint.x, startPoint.y, -difX, difY);
            }
        } else {
            if (difY < 0) { //左下->右上
                bounds = new Rectangle(startPoint.x, endPoint.y, difX, -difY);
            } else { //左上->右下
                bounds = new Rectangle(startPoint.x, startPoint.y, difX, difY);
            }
        }
        for (Shape s: Panel.getShapeList()) {
            shapeRange = new Rectangle(s.getP1().x, s.getP1().y, s.getWidth(), s.getHeight());
            //線會進去?
            if (bounds.contains(shapeRange)) {
                shapeInRange.add((BaseObj) s);
            }
        }
    }

}


