package editor_shape;

import java.awt.*;

public class UseCaseObj extends Shape {

    public UseCaseObj(Point p) {
        super(p, 100, 60);
        this.objName = "Obj Name";
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawOval(x1, y1, width, height);
        g.drawString(objName, x1 + 25, y1+35);
    }
}
