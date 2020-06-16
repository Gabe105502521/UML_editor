package editor_main;

import editor_mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ToolBar extends JPanel{
    private String[] nameList;
    private BaseObjMode[] modeList;
    private List<Button> buttonList;
    private MenuBar menuBar;
    private Canvas canvas;
    public ToolBar() {
        canvas = Canvas.getInstance();
        menuBar = MenuBar.getMenuBar();
        buttonList = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //垂直排

        nameList = new String[]{"select.png", "association.png", "generalization.png", "composition.png", "class.png", "useCase.png"};
        modeList = new BaseObjMode[]{new SelectMode(), new AssociationMode(), new GeneralizationMode(), new CompositionMode(), new ClassMode(), new UseCaseMode()};

        for(int i = 0; i < 6; i++){
            Button b = new Button(nameList[i], modeList[i]);
            b.setBackground( new Color(0,0,255)); //背景顏色隨便，只起佔位作用
            b.setOpaque(false);//設置背景透明
            b.setBorder(null);
            buttonList.add(b);
            this.add(b);
        }
    }
    private class Button extends JButton {
        BaseObjMode mode;
        public Button(String imgName, BaseObjMode mode) {
            this.setIcon(new ImageIcon(getClass().getResource("../Image/" + imgName)));
            //this.setContentAreaFilled(false);
            this.setBorderPainted(false);
            this.setPreferredSize(new Dimension(80, 80));
            this.mode = mode;
            this.addActionListener(new buttonListener());
        }

        class buttonListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetColor();
                ((JButton)e.getSource()).setOpaque(true);
                ((JButton)e.getSource()).setBackground(Color.LIGHT_GRAY);
                Canvas.getInstance().setCurrentMode(mode);
                setMenuUnable();
            }
        }
    }

    void resetColor() {
        for(Button btn: buttonList) {
            btn.setBackground( new Color(0,0,0));
            btn.setOpaque(false);
        }
    }

    void setMenuUnable() {
        menuBar.setNameItem(false);
        menuBar.setGroupItem(false);
        menuBar.setUnGroupItem(false);
    }

}

