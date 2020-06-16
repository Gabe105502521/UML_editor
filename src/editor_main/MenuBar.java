package editor_main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private static MenuBar menuBar = null;
    private JMenu fileMenu, editMenu;
    private JMenuItem groupItem, unGroupItem, nameItem;
    public MenuBar() {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        groupItem = new JMenuItem("Group");
        groupItem.addActionListener(new groupItemListener());
        groupItem.setEnabled(false);

        unGroupItem = new JMenuItem("UnGroup");
        unGroupItem.addActionListener(new unGroupItemListener());
        unGroupItem.setEnabled(false);

        nameItem = new JMenuItem("Change object name");
        nameItem.addActionListener(new nameItemListener());
        nameItem.setEnabled(false);

        editMenu.add(groupItem);
        editMenu.add(unGroupItem);
        editMenu.add(nameItem);

        this.add(fileMenu);
        this.add(editMenu);

    }

    public static MenuBar getMenuBar() {
        if (menuBar == null) {
            menuBar =  new MenuBar();
        }
        return menuBar;
    }

    class groupItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Canvas.getInstance().groupObj();
        }
    }
    class unGroupItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Canvas.getInstance().unGroupObj();
        }
    }
    class nameItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Canvas.getInstance().changeName();
            Canvas.getInstance().repaint();
        }
    }

    public void setGroupItem(boolean enable) {
        groupItem.setEnabled(enable);
    }

    public void setUnGroupItem(boolean enable) {
        this.unGroupItem.setEnabled(enable);
    }

    public void setNameItem(boolean enable) {
        this.nameItem.setEnabled(enable);
    }
}
