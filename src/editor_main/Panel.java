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
import java.util.Objects;

public class Panel extends JPanel {
    public static List<Shape> shapeList  = new ArrayList<>();
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
            public void mouseClicked(MouseEvent e) {
                UMLEditor.getCurrentMode().mouseClicked(e);
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

        //will my group occur null element in list?
        //shapeList.removeIf(Objects::isNull);

        for (Shape s: shapeList) {
//            System.out.println(s);
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(2.0f));
            s.draw(g2);
        }
    }

    public static List<Shape> getShapeList() {
        return shapeList;
    }

}
