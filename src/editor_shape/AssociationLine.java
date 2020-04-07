package editor_shape;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;

public class AssociationLine extends Line {
    public AssociationLine(Point p1, Point p2) {
        super(p1, p2);
        this.width = 10;
        this.height = 10;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
/*        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x2-10, y2+10);
        g.drawLine(x2, y2, x2-10, y2-10);
        */

        //by https://blog.csdn.net/xidiangejun/article/details/5525241

        //箭頭  微調
        g.drawLine(endPoint.x, endPoint.y, x3, y3);
        g.drawLine(endPoint.x, endPoint.y, x4, y4);
        g.fillRect(startPoint.x-5, startPoint.y-5, 10, 10);
        g.fillRect(endPoint.x-5, endPoint.y-5, 10, 10);
    }


}
