package editor_shape;

import java.awt.*;

public class CompositionLine extends Line{
    private int port;
    public CompositionLine(Point p1, Point p2, int port) {
        super(p1, p2);
        this.width = 10;
        this.height = 10;
        this.port = port;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if (port == 0) {
            x2 += 10;
        } else if (port == 1) {
            x2 -= 10;
        } else if (port == 2) {
            y2 += 10;
        } else if (port == 3) {
            y2 -= 10;
        }
        g.drawLine(x2, y2 - height/2, x2 - width/2, y2);
        g.drawLine(x2, y2 - height/2, x2 + width/2, y2);
        g.drawLine(x2 - width/2, y2, x2, y2 + height/2);
        g.drawLine(x2 + width/2, y2, x2, y2 + height/2);
    }
}
