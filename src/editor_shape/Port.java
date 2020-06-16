package editor_shape;

import java.awt.*;

public class Port extends Shape{
    private int width, height;
    public Port(Point p) {
        this.p1 = p;
        this.width = 10;
        this.height = 10;
        this.depth = 0; // let it won't be covered
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(p1.x - 5, p1.y - 5, width, height);
    }

    @Override
    public void adjust(int difX, int difY) {
        this.p1.setLocation(p1.x + difX, p1.y + difY);
    }

    @Override
    public boolean inside(Point p) {
        return false;
    }

}
