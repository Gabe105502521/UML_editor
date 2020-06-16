package editor_main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UMLEditor extends JFrame{
    private Canvas canvas;
    private JPanel toolBar;
    private MenuBar menuBar;

    public UMLEditor() {
        canvas = Canvas.getInstance();
        menuBar = MenuBar.getMenuBar();
        toolBar = new ToolBar();

        //取得內容面板再對其加入component
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
        this.getContentPane().add(canvas, BorderLayout.CENTER);
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

    public static void main(String argv[]) {
        new UMLEditor().init();
    }

}