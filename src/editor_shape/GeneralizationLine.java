package editor_shape;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class GeneralizationLine extends Line{

    public GeneralizationLine(Point p1, Point p2) {
        super(p1, p2);
        this.width = 10;
        this.height = 10;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        //三角形箭頭
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(x2, y2);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.closePath();
        g.draw(triangle);

    }
}
