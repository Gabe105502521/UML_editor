package editor_shape;

import java.awt.*;
public class UseCaseObj extends BaseObj {

    public UseCaseObj(Point p) {
        this.p1 = p;
        this.width = 100;
        this.height = 60;
        this.objName = "Obj Name";
        setPort();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawOval(p1.x, p1.y, width, height);
        g.drawString(objName, p1.x + 25, p1.y+35);
    }

}
