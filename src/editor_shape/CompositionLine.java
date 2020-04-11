package editor_shape;

import java.awt.*;

public class CompositionLine extends Line{

    public CompositionLine(Port startPort, Port endPort) {
        super(startPort, endPort);
        this.width = 10;
        this.height = 10;
    }


    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        //adjust
        endPoint = new Point ((x3 + x4) / 2, (y3 + y4) / 2);

        g.drawLine(endPoint.x, endPoint.y - height/2, endPoint.x - width/2, endPoint.y);
        g.drawLine(endPoint.x, endPoint.y - height/2, endPoint.x + width/2, endPoint.y);
        g.drawLine(endPoint.x - width/2, endPoint.y, endPoint.x, endPoint.y + height/2);
        g.drawLine(endPoint.x + width/2, endPoint.y, endPoint.x, endPoint.y + height/2);
    }
}
