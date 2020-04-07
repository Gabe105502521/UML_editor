package editor_mode;

import editor_main.Panel;
import editor_shape.ClassObj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ClassMode extends BaseMode {
    public ClassMode(Panel panel, Graphics g) {
        super(panel, g);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("?");
        super.mousePressed(e);
        shape = new ClassObj(e.getPoint());
        shape.draw((Graphics2D) g);
    }
}
