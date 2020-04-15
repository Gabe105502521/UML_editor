package editor_shape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class BaseObj extends Shape {

    protected String objName;
    protected List<Port> ports = new ArrayList<>();
    protected int xForAlign;
    @Override
    public void draw(Graphics2D g) {

    }

    public Port findNearestPort(Point p) {
        int i;
        double dis = 100000000;
        Port tmpPort = null;
        for (i = 0; i < 4; i++) {
            if (dis > getDistance(this.ports.get(i).p1, p)) {
                dis = getDistance(this.ports.get(i).p1, p);
                tmpPort = this.ports.get(i);
            }
        }
        return tmpPort;
    }

    public double getDistance(Point p1, Point p2){
        double r = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
        return r;
    }

    public void setPort() {
        ports.add(new Port(new Point(p1.x + width,  p1.y + height / 2)));
        ports.add(new Port(new Point(p1.x,  p1.y + height / 2)));
        ports.add(new Port(new Point(p1.x + width / 2,  p1.y + height)));
        ports.add(new Port(new Point(p1.x + width / 2,  p1.y)));
    }

    @Override
    public void adjust(int difX, int difY) {
        this.setP1(new Point(this.getP1().x + difX, this.getP1().y + difY));
        for (Shape s: this.getPorts()) {
            s.adjust(difX, difY);
        }
    }

    public void setObjName(String objName) {
        //centre adjustment, because one word's width seems to be about 6, so *6, and class and usecase have same width, so write here
        int halfLeft = (this.width - objName.length()*6)/ 2;
        this.xForAlign = halfLeft;
        this.objName = objName;
    }

    public List<Port> getPorts() {
        return ports;
    }

    @Override
    public boolean inRectangle(Rectangle bound) {
        Rectangle shapeRange = new Rectangle(this.getP1().x, this.getP1().y, this.getWidth(), this.getHeight());
        if (bound.contains(shapeRange))
            return true;
        else return false;
    }
}
