package editor_shape;

import java.awt.*;

public class BaseObj extends Shape {

    protected String objName;
    protected Port[] ports = new Port[4];

    @Override
    public void draw(Graphics2D g) {

    }

    public Port findNearestPort(Point p) {
        int i;
        double dis = 100000000;
        Port tmpPort = null;
        for (i = 0; i < 4; i++) {
            if (dis > getDistance(this.ports[i].p1, p)) {
                dis = getDistance(this.ports[i].p1, p);
                tmpPort = this.ports[i];
            }
        }
        return tmpPort;
    }

    public double getDistance(Point p1, Point p2){
        double r = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
        return r;
    }

    public void setPort() {
        ports[0] = new Port(new Point(p1.x + width,  p1.y + height / 2));
        ports[1] = new Port(new Point(p1.x,  p1.y + height / 2));
        ports[2] = new Port(new Point(p1.x + width / 2,  p1.y + height));
        ports[3] = new Port(new Point(p1.x + width / 2,  p1.y));
    }

    @Override
    public void adjust(int difX, int difY) {
        this.setP1(new Point(this.getP1().x + difX, this.getP1().y + difY));
        for (Shape s: this.getPorts()) {
            s.adjust(difX, difY);
        }
    }

    public Port[] getPorts() {
        return ports;
    }
}
