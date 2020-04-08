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
    private Point startPoint, endPoint;
    private boolean dragging = false, hasShape = false;

    private List<Shape> shapeList, shapeInRange;
    private int oldSelect;  //maybe可以改成shape有select狀態
    private Point closestPort;
    private int port;
    public DrawListener(Panel panel) {
        this.mainPanel = panel;
        shapeList = new ArrayList<>();
        shapeInRange = new ArrayList<>();
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (UMLEditor.currentMode == 1 && startShape != -1) {
            selectShape();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (UMLEditor.currentMode == 1) {
            startShape = checkHasShape(e.getPoint());
            startPoint = e.getPoint();
        }
        if (UMLEditor.currentMode == 2 || UMLEditor.currentMode == 3 || UMLEditor.currentMode == 4) {
            startShape = checkHasShape(e.getPoint());
            startPoint = closestPort;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endShape = checkHasShape(e.getPoint());
        endPoint = e.getPoint();
        if (UMLEditor.currentMode == 1) {
            endPoint = e.getPoint();
            selectShape();
            dragging = false;
        }
        if ((UMLEditor.currentMode == 2  || UMLEditor.currentMode == 3 || UMLEditor.currentMode == 4)
                && startPoint != null && dragging && endShape != -1 && startShape != endShape) {
            dragging = false;
            endPoint = closestPort;
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
        endPoint = null;
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
            dragging = true;
//            shape = new AssociationLine(startPoint, endPoint);
////            shape.draw((Graphics2D)g);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public int checkHasShape(Point p) {
        int cnt = 0;
        //    0       1      2      3
        double dis = 10000000;
        for (Shape s: shapeList) {
            if (p.x >= s.getX1() && p.x <= s.getX1() + s.getWidth()) {
                if (p.y >= s.getY1() && p.y <= s.getY1() + s.getHeight()) {
                    for (int i = 0; i < 4; i++) {
                        if (dis > getDistance(s.getPortList().get(i), p)) {
                            dis = getDistance(s.getPortList().get(i), p);
                            closestPort = s.getPortList().get(i);
                            port = i;
                        }
                    }
                    return cnt;
                }
            }
            cnt++;
        }
        return -1;
    }

    public double getDistance(Point p1, Point p2){
        double r = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
        return r;
    }

    public void selectShape() {
        //only erase be selected or g.clearRec
        if (startShape == -1) {
            findshapes(startPoint, endPoint);
            for (Shape s : shapeList) {
                if (shapeInRange.contains(s)) {
                    s.setBeSelect(true);
                    s.selected((Graphics2D) g);
                } else {
                    s.setBeSelect(false);
                    s.unSelected((Graphics2D) g);
                }
            }
        }else if (startShape != -1) {
            Shape s = shapeList.get(startShape);
            if (startShape != -1 && dragging == false) {
                for (Shape s1 : shapeList) {
                    if (s1.isBeSelect() == true && !s1.equals(s)) {
                        s1.setBeSelect(false);
                        s1.unSelected((Graphics2D) g);
                    }
                }
                s.setBeSelect(true);
                s.selected((Graphics2D) g);
            } else if (startShape != -1 && dragging) {
                s.unSelected((Graphics2D) g);
                s.setBeSelect(false);
                s.setX1(s.getX1() + endPoint.x - startPoint.x);
                s.setY1(s.getY1() + endPoint.y - startPoint.y);
                s.adjust();
            }
        }
        myRepaint();
        shapeInRange.clear();
    }

    public void myRepaint() {
        for (Shape s: shapeList) {
            s.draw((Graphics2D)g);
        }
    }

    public void findshapes(Point p1, Point p2) {
        int difX = p2.x - p1.x;
        int difY = p2.y - p1.y;
        for (Shape s: shapeList) {
            //能更精簡?
            if ((s.getX1() >= p1.x && s.getX1() <= p1.x + difX) &&  //左上->右下
                    (s.getY1() >= p1.y && s.getY1() <= p1.y + difY)) {
                System.out.println("0");
                shapeInRange.add(s);
            } else if ((s.getX1() <= p1.x && s.getX1() >= p1.x + difX) &&  //右上->左下
                    (s.getY1() >= p1.y && s.getY1() <= p1.y + difY)) {
                System.out.println("1");
                shapeInRange.add(s);
            } else if ((s.getX1() >= p1.x && s.getX1() <= p1.x + difX) &&  //左下->右上
                    (s.getY1() <= p1.y && s.getY1() >= p1.y + difY)) {
                System.out.println("2");
                shapeInRange.add(s);
            } else if ((s.getX1() <= p1.x && s.getX1() >= p1.x + difX) && //右下->左上
                    (s.getY1() <= p1.y && s.getY1() >= p1.y + difY)) {
                System.out.println("3");
                shapeInRange.add(s);
            }
        }
    }

}
