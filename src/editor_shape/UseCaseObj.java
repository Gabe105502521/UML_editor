package editor_shape;

import java.awt.*;
public class UseCaseObj extends Shape {

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
        //g.setColor(Color.WHITE);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(p1.x, p1.y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(p1.x, p1.y, width, height);
        //Font f1 = new Font(Font.BOLD);
        //g.setFont(f1);
        g.drawString(objName, p1.x + xForAlign, p1.y+35);

        if (this.isSelected) {
            for (Port p : ports) {
                p.draw(g);
            }
        }
    }

}
