package editor_mode;

import editor_main.MenuBar;
import editor_shape.Shape;
import java.awt.*;
import java.awt.event.MouseEvent;

public class SelectMode extends BaseObjMode {
    private int tmp;
    private editor_main.MenuBar menuBar;

    public SelectMode() {
        this.menuBar = MenuBar.getMenuBar();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        tmp = checkInShape(startPoint); // if not, it will be -//
        removeOldPort();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        menuBar.setNameItem(false);
        menuBar.setUnGroupItem(false);
        menuBar.setUnGroupItem(false);
        menuBar.setGroupItem(false);
        if (!endPoint.equals(startPoint)) {         //dragging
            int difX = endPoint.x - startPoint.x, difY = endPoint.y - startPoint.y;
            if (tmp != -1) {  // startpoint has a shape
                Shape tmpShape = canvas.getShapeList().get(tmp);

                tmpShape.adjust(difX, difY);
                tmpShape.setDepth(99); //reset
                tmpShape.checkOverlap();

            } else if (tmp == -1) {
                selectShapes(difX, difY);
                for (Shape s : canvas.getShapeSelected()) {
                    s.setSelected(true);
                }
            }
            if (canvas.getShapeSelected().size() >= 2) {
                menuBar.setGroupItem(true);
            }
            //click
        } else {
            if (tmp != -1) {
                Shape tmpShape = canvas.getShapeList().get(tmp);
                tmpShape.setSelected(true);
                canvas.getShapeSelected().add(tmpShape);
                tmpShape.checkOverlap();
            }
        }

        //要求是一個基本物件處於被 select 的狀態時
        if (canvas.getShapeSelected().size() == 1) {
            menuBar.setNameItem(true);
        }
        //return不是null代表是group
        if (canvas.getShapeSelected().size() != 0 &&
                canvas.getShapeSelected().get(0).getShapesIngroup() != null) {
            menuBar.setUnGroupItem(true);
        }
    }


    //select shapes that are in range
    public void selectShapes(int difX, int difY) {
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
        for (Shape s : canvas.getShapeList()) {
            if (s.inRectangle(bounds)) {
                canvas.getShapeSelected().add(s);
            }
        }
    }
}