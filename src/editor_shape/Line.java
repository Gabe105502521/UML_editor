package editor_shape;

import java.awt.*;

public class Line extends Shape{
    protected Point startPoint, endPoint;
    protected Port startPort, endPort;

    protected int width, height;
    int x3 = 0;
    int y3 = 0;
    int x4 = 0;
    int y4 = 0;

    public Line(Port startPort, Port endPort) {
        this.startPort = startPort;
        this.endPort = endPort;
        this.startPoint = startPort.p1;
        this.endPoint = endPort.p1;
    }

    @Override
    public boolean inside(Point p) {
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        Point startPoint = startPort.p1, endPoint = endPort.p1;
        double awrad = Math.atan(width / height); // 箭頭角度
        double arrow_len = Math.sqrt(width * width + height * height); // 箭頭的長度
        double[] arrXY_1 = rotateVec(endPoint.x - startPoint.x, endPoint.y - startPoint.y, awrad, true, arrow_len);
        double[] arrXY_2 = rotateVec(endPoint.x - startPoint.x, endPoint.y - startPoint.y, -awrad, true, arrow_len);
        double x_3 = endPoint.x - arrXY_1[0]; // (x3,y3)是第一端點
        double y_3 = endPoint.y - arrXY_1[1];
        double x_4 = endPoint.x - arrXY_2[0]; // (x4,y4)是第二端點
        double y_4 = endPoint.y - arrXY_2[1];

        Double X3 = x_3;
        x3 = X3.intValue();
        Double Y3 = y_3;
        y3 = Y3.intValue();
        Double X4 = x_4;
        x4 = X4.intValue();
        Double Y4 = y_4;
        y4 = Y4.intValue();
        //起始線
        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

        //方形
        g.fillRect(startPoint.x-5, startPoint.y-5, 10, 10);
        g.fillRect(endPoint.x-5, endPoint.y-5, 10, 10);
    }

    protected double[] rotateVec(int px, int py, double ang,
                              boolean isChLen, double newLen) {
        double mathstr[] = new double[2];
        // 向量旋轉函式，引數含義分別是x分量、y分量、旋轉角、是否改變長度、新長度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }

}
