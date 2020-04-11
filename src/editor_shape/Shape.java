package editor_shape;

import java.awt.*;

public abstract class Shape {
    protected Point p1, p2;
    protected int width, height;

    public abstract void draw(Graphics2D g);

    public boolean inside(Point p) {
        if (p.x >= p1.x && p.x <= p1.x + width) {
            if (p.y >= p1.y && p.y <= p1.y + height) {
                return true;
            }
        }
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void adjust(int difX, int difY) {
    }
    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }
}

