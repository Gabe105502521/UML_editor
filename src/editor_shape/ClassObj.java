package editor_shape;

import java.awt.*;

public class ClassObj extends Shape {

    public ClassObj(Point p) {
        super(p);
        this.width = 100;
        this.height = 125;
        this.x1 = p.x;
        this.y1 = p.y;
        this.x2 = p.x + width;
        this.y2 = p.y + height;
        this.objName = "Obj Name";
    }

    @Override
    public void draw(Graphics2D g) {
        int spacing = this.height / 3;
        g.drawLine(x1, y1 + spacing, x2 , y1  + spacing);
        g.drawLine(x1, y1 + spacing * 2, x2, y1  + spacing * 2);
        g.drawRect(x1, y1, width, height);
        g.drawString(objName, x1 + 25, y1 + 25);
    }
}
