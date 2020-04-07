package editor_main;

import editor_shape.*;
import editor_shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class DrawListener implements MouseMotionListener, MouseListener {

    private JPanel mainPanel;
    private Graphics g;
    private int  startShape=0, endShape = 0;
    private Shape shape;
    private Point startPoint;
    private boolean dragging = false, hasShape = false;
    private List<Shape> shapeList;
    private Point closestPort;
    private int port;
    public DrawListener(Panel panel) {
        this.mainPanel = panel;
        shapeList = new ArrayList<>();
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (UMLEditor.currentMode == 2 || UMLEditor.currentMode == 3 || UMLEditor.currentMode == 4) {
            startShape = checkHasShape(e.getPoint());
            startPoint = closestPort;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endShape = checkHasShape(e.getPoint());
        if ((UMLEditor.currentMode == 2  || UMLEditor.currentMode == 3 || UMLEditor.currentMode == 4)
                && startPoint != null && dragging && endShape != 0 && startShape != endShape) {
            dragging = false;
            Point endPoint = closestPort;
            if (UMLEditor.currentMode == 2) {
                shape = new AssociationLine(startPoint, endPoint);
            } else if (UMLEditor.currentMode == 3){
                shape = new GeneralizationLine(startPoint, endPoint);
            } else {
                shape = new CompositionLine(startPoint, endPoint, port);
                System.out.println(port);
            }
            shape.draw((Graphics2D)g);
        } else if (UMLEditor.currentMode == 5) {
            shape = new ClassObj(e.getPoint());
            shape.draw((Graphics2D)g);
            shapeList.add(shape);
        } else if (UMLEditor.currentMode == 6) {
            shape = new UseCaseObj(e.getPoint());
            shape.draw((Graphics2D)g);
            shapeList.add(shape);
        }
        startPoint = null;
        closestPort = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if((UMLEditor.currentMode == 2 || UMLEditor.currentMode == 3 || UMLEditor.currentMode == 4) && startPoint != null) {
            dragging = true;
//            shape = new AssociationLine(startPoint, endPoint);
////            shape.draw((Graphics2D)g);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public int checkHasShape(Point p) {
        int cnt = 1;
        //    0       1      2      3
        Point portE , portW, portS, portN;
        double dis = 10000000;
        for (Shape s: shapeList) {
            portE = new Point(s.getX1() + s.getWidth(),  s.getY1() + s.getHeight()/2);
            portW = new Point(s.getX1(),  s.getY1() + s.getHeight()/2);
            portS = new Point(s.getX1() + s.getWidth()/2,  s.getY1() + s.getHeight());
            portN = new Point(s.getX1() + s.getWidth()/2,  s.getY1());
            Point[] portList = {portE, portW, portS, portN};
            if (p.x >= s.getX1() && p.x <= s.getX1() + s.getWidth()) {
                if (p.y >= s.getY1() && p.y <= s.getY1() + s.getHeight()) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println(portList[i]);
                        if (dis > getDistance(portList[i], p)) {
                            dis = getDistance(portList[i], p);
                            closestPort = portList[i];
                            port = i;
                        }
                    }
                    return cnt;
                }
            }
            cnt++;
        }
        return 0;
    }

    public double getDistance(Point p1, Point p2){
        double r = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
        return r;
    }
}
