package editor_main;


import editor_mode.BaseMode;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventListener;

public class UMLEditor extends JFrame{
    private  Panel panel;
    private DrawListener listener;
    //public static BaseMode currentMode = null;
    public static int currentMode = 0;
    private ToolBar toolBar;
    private MenuBar menuBar;

    public UMLEditor() {
        panel = new Panel();
        menuBar = new MenuBar();
        toolBar = new ToolBar();

        //取得內容面板再對其加入component
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        this.getContentPane().add(toolBar, BorderLayout.WEST);
    }
    private void init() {


        this.setTitle("UMLEditor");
        this.setSize(1000,750);
        this.setLocationRelativeTo(null);  //置中
        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    int result=JOptionPane.showConfirmDialog((Component)e.getSource(),
                            "Are you sure you want to exit?",
                            "Confirm Exit",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
                    if (result==JOptionPane.YES_OPTION) {System.exit(0);}
                }
            });
        listener = new DrawListener(panel);
        panel.addMouseListener(listener);
        panel.addMouseMotionListener(listener);
        //getGraphics需在setVisible之後
        listener.setG(panel.getGraphics());
    }



    public static void main(String argv[]) {
        new UMLEditor().init();
    }

}