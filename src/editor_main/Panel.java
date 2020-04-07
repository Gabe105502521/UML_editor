package editor_main;

import editor_shape.ClassObj;

import javax.swing.*;
import editor_shape.Shape;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Panel extends JPanel {
    private int x = 20;
    private int y = 20;
    private Shape shape;
    private Graphics g;
    private DrawListener listener;
    //1 for select, 2 for association, and so on
    //public static int mode = 1;

    public Panel()  {
    }

    protected void paintComponent( Graphics g ){
        /*shape = new ClassObj(x, y, 10, 10);
        shape.draw((Graphics2D) g);*/
        //super.paintComponent(g);

    }

}
