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
    private static List<BaseObj> shapesInRange = new ArrayList<>();
    private static List<GroupObj> groupObjList = new ArrayList<>();
    private editor_main.MenuBar menuBar;
    private static BaseObj shapeSelected = null;

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
            if (tmp != -1) {  // has a shape
                removeOldPort();
                tmp = checkInShape(startPoint); // again after remove to avoid error by index of list
                System.out.println(tmp + " " + Panel.getShapeList().size());
                Panel.getShapeList().get(tmp).adjust(difX, difY);
                menuBar.setGroupItem(false);
            } else if (tmp == -1) {
                selectShapes(difX, difY);
                for (BaseObj obj : shapesInRange) {
                    for (int i = 0; i < obj.getPorts().size(); i++) {
                        getShapeList().add(obj.getPorts().get(i));
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
        tmp = checkInShape(startPoint);
        if (tmp != -1) {
            System.out.println(tmp);
            BaseObj tmpShape = (BaseObj) getShapeList().get(tmp);
            removeOldPort();
            for (int i = 0; i < tmpShape.getPorts().size(); i++) {
                getShapeList().add(tmpShape.getPorts().get(i));
            }
            if (tmpShape instanceof GroupObj)
                menuBar.setUnGroupItem(true);
            shapeSelected = tmpShape;
            if (shapeSelected instanceof GroupObj)
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
                shapesInRange.add((BaseObj) s);
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
        for (int i = 0; i < Panel.getShapeList().size(); i++) {
            System.out.println(Panel.getShapeList().get(i));
        }

        //Because shapesInRange is static, if it change, by groupobj's constructor, it will have same memory as shapeInRange,
        //and when I call shapesInRange.clear(), list in group will be clear
        List<BaseObj> tmp = new ArrayList<>();
        tmp.addAll(shapesInRange);

        //remove port(if it have)
        Iterator<BaseObj> iterator = tmp.iterator();
        while (iterator.hasNext()) {
            BaseObj s = iterator.next();
            if (s.getClass().equals(Port.class)) {
                iterator.remove();
            }
        }
        //because my for always from 0 to end
        Panel.getShapeList().add(0, new GroupObj(tmp));
    }

    public static void unGroupObj() {
        if (shapeSelected instanceof GroupObj)
            Panel.getShapeList().remove(shapeSelected);
    }


}