package editor_main;

import editor_mode.BaseObjMode;
import editor_mode.SelectMode;

import javax.swing.*;
import editor_shape.Shape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
public class Panel extends JPanel {
    public static List<Shape> shapeList;
    public Panel()  {
        shapeList = new ArrayList<>();

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
            public void mouseClicked(MouseEvent e) {
                UMLEditor.getCurrentMode().mouseClicked(e);
                repaint();
            }
        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape s: shapeList) {
            s.draw((Graphics2D)g);
        }
    }

    public static List<Shape> getShapeList() {
        return shapeList;
    }

    public static void setShapeList(List<Shape> shapeLisT) {
        Panel.shapeList = shapeLisT;
    }


}
