package editor_main;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private JMenu fileMenu, editMenu;
    private Panel panel;
    public MenuBar() {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        this.add(fileMenu);
        this.add(editMenu);
    }
}
