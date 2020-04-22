package editor_main;


import editor_mode.BaseObjMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UMLEditor extends JFrame{
    private static Panel panel;
    private static BaseObjMode currentMode;
    //public static int currentMode = 0;
    private JPanel toolBar;
    private MenuBar menuBar;

    public UMLEditor() {
        panel = new Panel();
        menuBar = MenuBar.getMenuBar();
        menuBar.setPanel(panel);
        currentMode = new BaseObjMode();
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
    }

    public static BaseObjMode getCurrentMode() {
        return currentMode;
    }

    public static void setCurrentMode(BaseObjMode currentMode) {
        UMLEditor.currentMode = currentMode;
    }

    public static void main(String argv[]) {
        new UMLEditor().init();
    }

}