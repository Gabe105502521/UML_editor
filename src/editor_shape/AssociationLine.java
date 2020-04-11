package editor_shape;

import java.awt.*;

public class AssociationLine extends Line {
    public AssociationLine(Port startPort, Port endPort) {
        super(startPort, endPort);
        this.width = 10;
        this.height = 10;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        //by https://blog.csdn.net/xidiangejun/article/details/5525241
        //箭頭  微調
        g.drawLine(endPoint.x, endPoint.y, x3, y3);
        g.drawLine(endPoint.x, endPoint.y, x4, y4);
        g.fillRect(startPoint.x-5, startPoint.y-5, 10, 10);
        g.fillRect(endPoint.x-5, endPoint.y-5, 10, 10);
    }


}
