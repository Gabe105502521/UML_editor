package editor_main;

import editor_mode.AssociationMode;
import editor_mode.ClassMode;
import editor_mode.SelectMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.CollationKey;

public class ToolBar extends JToolBar{
    private JPanel panel;
    private Button selectBtn;
    private Button associationBtn;
    private Button generalizationBtn;
    private Button compositionBtn;
    private Button classBtn;
    private Button useCaseBtn;
    private Button[] buttonList;
    public ToolBar() {
        panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); //垂直排
        this.setOrientation(SwingConstants.VERTICAL);


        selectBtn = new Button("select1.png");
        selectBtn.addActionListener(new selectBtnListener());

        associationBtn = new Button("association.png");
        associationBtn.addActionListener(new associationBtnListener());

        generalizationBtn = new Button("generalization.png");
        generalizationBtn.addActionListener(new generalizationBtnListener());

        compositionBtn = new Button("composition.png");
        compositionBtn.addActionListener(new compositionBtnListener());

        classBtn = new Button("class.png");
        classBtn.addActionListener(new classBtnListener());

        useCaseBtn = new Button("useCase.png");
        useCaseBtn.addActionListener(new useCaseBtnListener());

        buttonList = new Button[]{selectBtn, associationBtn, generalizationBtn, compositionBtn, classBtn, useCaseBtn};
        for(Button btn: buttonList) {
            btn.setBackground( new Color(0,0,255)); //背景顏色隨便，只起佔位作用
            btn.setOpaque(false);//設置背景透明
            btn.setBorder(null);
            panel.add(btn);
        }
        this.add(panel);

    }

    //use inner class to listen buttons
    class selectBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resetColor();
            selectBtn.setOpaque(true);
            selectBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.currentMode = 1;
        }
    }

    class associationBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resetColor();
            associationBtn.setOpaque(true);
            associationBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.currentMode = 2;
        }
    }

    class generalizationBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resetColor();
            generalizationBtn.setOpaque(true);
            generalizationBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.currentMode = 3;
        }
    }

    class compositionBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resetColor();
            compositionBtn.setOpaque(true);
            compositionBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.currentMode = 4;
        }
    }

    class classBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resetColor();
            classBtn.setOpaque(true);
            classBtn.setBackground(Color.LIGHT_GRAY);
            //UMLEditor.setCurrentMode(new ClassMode(mainPanel, mainPanel.getGraphics()));
            UMLEditor.currentMode = 5;
        }
    }

    class useCaseBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resetColor();
            useCaseBtn.setOpaque(true);
            useCaseBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.currentMode = 6;
        }
    }

    void resetColor() {
        for(Button btn: buttonList) {
            btn.setBackground( new Color(0,0,0));
            btn.setOpaque(false);
        }
    }
}

