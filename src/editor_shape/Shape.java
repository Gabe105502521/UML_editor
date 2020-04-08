package editor_shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Shape {
    protected int x1, y1, x2, y2, width, height;
    protected String objName;
    protected Point portE, portW, portS, portN;
    protected List<Point> portList;
    protected boolean beSelect = false;
    public Shape(Point p, int width, int height) {
        this.x1 = p.x;
        this.y1 = p.y;
        this.width = width;
        this.height = height;
/*        portE = new Point(s.getX1() + s.getWidth(),  s.getY1() + s.getHeight()/2);
        portW = new Point(s.getX1(),  s.getY1() + s.getHeight()/2);
        portS = new Point(s.getX1() + s.getWidth()/2,  s.getY1() + s.getHeight());
        portN = new Point(s.getX1() + s.getWidth()/2,  s.getY1());
  */
        portList = new ArrayList<>();

        portE = new Point(p.x + width,  p.y + height / 2);
        portW = new Point(p.x,  p.y + height / 2);
        portS = new Point(p.x + width / 2,  p.y + height);
        portN = new Point(p.x + width / 2,  p.y);
        portList.add(portE);
        portList.add(portW);
        portList.add(portS);
        portList.add(portN);
    }

    public Shape(Point p1, Point p2) {
        this.x1 = p1.x;
        this.y1 = p1.y;
        this.x2 = p2.x;
        this.y2 = p2.y;
    }

    public void selected(Graphics2D g) {
        paintPort(g);
    }

    public void unSelected(Graphics2D g) {
        clearSelf(g);
    }


    private void clearSelf(Graphics2D g) {
        //+-5 for erase port, maybe should make a function in shape to erase it, can also be used when click blank place
        g.clearRect(getX1()-5, getY1()-5, getWidth()+10, getHeight()+10);
    }
    private void paintPort(Graphics2D g) {
        g.fillRect(getPortE().x - 5, getPortE().y - 5, 10, 10);
        g.fillRect(getPortW().x - 5, getPortW().y - 5, 10, 10);
        g.fillRect(getPortS().x - 5, getPortS().y - 5, 10, 10);
        g.fillRect(getPortN().x - 5, getPortN().y - 5, 10, 10);
    }

    public void adjust() {
        portList.get(0).setLocation(x1 + width,  y1 + height / 2);
        portList.get(1).setLocation(x1,  y1 + height / 2);
        portList.get(2).setLocation(x1 + width / 2,  y1 + height);
        portList.get(3).setLocation(x1 + width /2,  y1);
    }
    public abstract void draw(Graphics2D g);

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Point> getPortList() {
        return portList;
    }

    public void setPortList(List<Point> portList) {
        this.portList = portList;
    }

    public Point getPortE() {
        return portE;
    }

    public void setPortE(Point portE) {
        this.portE = portE;
    }

    public Point getPortW() {
        return portW;
    }

    public void setPortW(Point portW) {
        this.portW = portW;
    }

    public Point getPortS() {
        return portS;
    }

    public void setPortS(Point portS) {
        this.portS = portS;
    }

    public Point getPortN() {
        return portN;
    }

    public void setPortN(Point portN) {
        this.portN = portN;
    }

    public boolean isBeSelect() {
        return beSelect;
    }

    public void setBeSelect(boolean beSelect) {
        this.beSelect = beSelect;
    }
}

