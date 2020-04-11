package editor_shape;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class GeneralizationLine extends Line{
    public GeneralizationLine(Port startPort, Port endPort) {
        super(startPort, endPort);
        this.width = 10;
        this.height = 10;
    }


    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        //三角形箭頭
        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(endPoint.x, endPoint.y);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.closePath();
        g.draw(triangle);

    }
}
