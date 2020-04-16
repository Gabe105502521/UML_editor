package editor_shape;

import java.awt.*;

public class ClassObj extends BaseObj {

    public ClassObj(Point p) {
        this.width = 100;
        this.height = 125;
        this.xForAlign = 25;
        this.p1 = p;
        this.objName = "Obj Name";
        setPort();
    }

    @Override
    public void draw(Graphics2D g) {
        p2 =  new Point(p1.x + width, p1.y);
        int spacing = height / 3;
        g.drawLine(p1.x, p1 .y + spacing, p2.x , p2.y  + spacing);
        g.drawLine(p1.x, p1.y + spacing * 2, p2.x, p1.y  + spacing * 2);
        g.drawRect(p1.x, p1.y, width, height);
        g.drawString(objName, p1.x + xForAlign, p1.y + 25);
    }



}
