package editor_shape;

import java.awt.*;
public class UseCaseObj extends BaseObj {

    public UseCaseObj(Point p) {
        this.p1 = p;
        this.width = 100;
        this.height = 60;
        this.xForAlign = 25;
        this.objName = "Obj Name";
        setPort();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawOval(p1.x, p1.y, width, height);
        //Font f1 = new Font(Font.BOLD);
        //g.setFont(f1);
        g.drawString(objName, p1.x + xForAlign, p1.y+35);
    }

}
