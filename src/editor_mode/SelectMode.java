package editor_mode;

import editor_main.MenuBar;
import editor_main.Panel;
import editor_main.UMLEditor;
import editor_shape.*;
import editor_shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static editor_main.Panel.getShapeList;

public class SelectMode extends BaseObjMode {
    private int tmp;
    private static List<Shape> shapesInRange = new ArrayList<>();
    private editor_main.MenuBar menuBar;
    private static Shape shapeSelected = null;

    public SelectMode() {
        this.menuBar = MenuBar.getMenuBar();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
        tmp = checkInShape(startPoint); // if not, it will be -1
        if (tmp == -1) {
            removeOldPort();
            shapesInRange.clear();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = e.getPoint();
        if (endPoint != startPoint) {         //dragging
            //not click, then set it false
            menuBar.setNameItem(false);
            menuBar.setUnGroupItem(false);

            int difX = endPoint.x - startPoint.x, difY = endPoint.y - startPoint.y;
            if (tmp != -1) {  // startpoint has a shape
                removeOldPort();
                tmp = checkInShape(startPoint); // again after remove to avoid error by index of list
                Shape tmpShape = Panel.getShapeList().get(tmp);

                boolean isInGroup = false;
                for (GroupObj g:Panel.getGroupObjList()) {
                    if (g.isContain(tmpShape)) {
                        g.adjust(difX, difY);
                        isInGroup = true;
                        break;
                    }
                }
                if (!isInGroup) {
                    tmpShape.adjust(difX, difY);
                    tmpShape.setDepth(99); //reset
                    tmpShape.checkOverlap();
                }
                menuBar.setGroupItem(false);
            } else if (tmp == -1) {
                selectShapes(difX, difY);
                for (Shape s : shapesInRange) {
                    for (int i = 0; i < s.getPorts().size(); i++) {
                        getShapeList().add(s.getPorts().get(i));
                    }
                }
            }
            if (shapesInRange.size() >= 2) {
                menuBar.setGroupItem(true);
            } else {
                menuBar.setGroupItem(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean isInGroup = false;
        tmp = checkInShape(startPoint);
        if (tmp != -1) {
            Shape tmpShape = getShapeList().get(tmp);
            removeOldPort();
            tmpShape.checkOverlap();
            for (GroupObj g:Panel.getGroupObjList()) {
                if (g.isContain(tmpShape)) {
                    for (int i = 0; i < g.getPorts().size(); i++) {
                        getShapeList().add(g.getPorts().get(i));
                    }
                    isInGroup = true;
                    menuBar.setUnGroupItem(true);
                    break;
                }
            }
            if (!isInGroup) {
                for (int i = 0; i < tmpShape.getPorts().size(); i++) {
                    getShapeList().add(tmpShape.getPorts().get(i));
                }
            }
            shapeSelected = tmpShape;
            if (isInGroup)
                menuBar.setUnGroupItem(true);
            else
                menuBar.setNameItem(true);

        } else {
            menuBar.setNameItem(false);
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
        for (Shape s : Panel.getShapeList()) {
            if (s.inRectangle(bounds) && !(s instanceof GroupObj)) {
                shapesInRange.add(s);
            }
        }
    }

    public static void changeName() {
        String input;
        JFrame f = new JFrame("JOptionPane Test");
        f.setSize(400, 300);
        f.setLocationRelativeTo(null);
        input = JOptionPane.showInputDialog("Input your new object name");
        if (input != null && input != "")
            shapeSelected.setObjName(input);
    }

    public static void groupObj() {
        /*for (int i = 0; i < Panel.getShapeList().size(); i++) {
            System.out.println(Panel.getShapeList().get(i));
        }*/

        //Because shapesInRange is static, if it change, by groupobj's constructor, it will have same memory as shapeInRange,
        //and when I call shapesInRange.clear(), list in group will be clear
        List<Shape> tmp = new ArrayList<>();
        tmp.addAll(shapesInRange);
        /*test
        for (int i = 0; i < Panel.getShapeList().size(); i++){
            for (int j = 0; j < tmp.size(); j++) {
                if (tmp.get(j) == Panel.getShapeList().get(i)) {
                    System.out.println("!!!!");
                }
            }
        }*/
        //remove port(if it have)
        Iterator<Shape> iterator = tmp.iterator();
        while (iterator.hasNext()) {
            Shape s = iterator.next();
            if (s instanceof Port) {
                iterator.remove();
            }
        }
        //because my for always from 0 to end
        Panel.getGroupObjList().add(0, new GroupObj(tmp));
    }

    public static void unGroupObj() {
        GroupObj tmpG = null;
        for (GroupObj g:  Panel.getGroupObjList()) {
            if (g.isContain(shapeSelected)) {
                tmpG = g;
                break;
            }
        }
        Panel.getGroupObjList().remove(tmpG);
    }
}