package editor_mode;

import editor_main.Panel;
import editor_shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BaseMode implements MouseListener, MouseMotionListener {
    protected Panel mainPanel;
    protected Shape shape;
    protected Graphics g;
    public BaseMode(Panel panel, Graphics g) {
        mainPanel = panel;
        this.g = g;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
