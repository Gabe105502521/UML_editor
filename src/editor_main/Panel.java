package editor_main;

import editor_mode.BaseObjMode;
import editor_mode.SelectMode;

import javax.swing.*;

import editor_shape.GroupObj;
import editor_shape.Line;
import editor_shape.Shape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Panel extends JPanel {
    private static List<Shape> shapeList  = new ArrayList<>();
    private static List<Line> lineList  = new ArrayList<>();
    private static List<GroupObj> groupObjList  = new ArrayList<>();

    private MenuBar menuBar;
    public Panel()  {
        menuBar = MenuBar.getMenuBar();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                UMLEditor.getCurrentMode().mousePressed(e);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                UMLEditor.getCurrentMode().mouseReleased(e);
                // if not select mode, then don't selcet any one
                if (!UMLEditor.getCurrentMode().getClass().equals(SelectMode.class)) {
                    BaseObjMode.removeOldPort();
                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                menuBar.setNameItem(false);
            }

        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2.0f));

        Collections.sort(shapeList,new depthComparator());

        for (Shape s: shapeList) {
            s.draw(g2);
        }
        for (Line l: lineList) {
            l.draw(g2);
        }
    }

    public static List<Shape> getShapeList() {
        return shapeList;
    }
    public static List<Line> getSLineList() {
        return lineList;
    }
    public static List<GroupObj> getGroupObjList() {
        return groupObjList;
    }

    class depthComparator implements Comparator<Shape> {
        public int compare(Shape s1, Shape s2) {
            return s2.getDepth() - s1.getDepth();  //倒序
        }
    }
}
