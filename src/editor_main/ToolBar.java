package editor_main;

import editor_mode.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar extends JPanel{
    private Button selectBtn;
    private Button associationBtn;
    private Button generalizationBtn;
    private Button compositionBtn;
    private Button classBtn;
    private Button useCaseBtn;
    private Button[] buttonList;
    private MenuBar menuBar;
    public ToolBar() {
        menuBar = MenuBar.getMenuBar();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //垂直排
        selectBtn = new Button("select.png");
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
            this.add(btn);
        }
    }

    //use inner class to listen buttons
    class selectBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            resetColor();
            selectBtn.setOpaque(true);
            selectBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.setCurrentMode(new SelectMode());
            //UMLEditor.currentMode = 1;
        }
    }

    class associationBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setMenuUnable();
            resetColor();
            associationBtn.setOpaque(true);
            associationBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.setCurrentMode(new AssociationMode());
            //UMLEditor.currentMode = 2;
        }
    }

    class generalizationBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setMenuUnable();
            resetColor();
            generalizationBtn.setOpaque(true);
            generalizationBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.setCurrentMode(new GeneralizationMode());
            //UMLEditor.currentMode = 3;
        }
    }

    class compositionBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setMenuUnable();
            resetColor();
            compositionBtn.setOpaque(true);
            compositionBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.setCurrentMode(new CompositionMode());

            //UMLEditor.currentMode = 4;
        }
    }

    class classBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setMenuUnable();
            resetColor();
            classBtn.setOpaque(true);
            classBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.setCurrentMode(new ClassMode());
            //UMLEditor.currentMode = 5;
        }
    }

    class useCaseBtnListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            setMenuUnable();
            resetColor();
            useCaseBtn.setOpaque(true);
            useCaseBtn.setBackground(Color.LIGHT_GRAY);
            UMLEditor.setCurrentMode(new UseCaseMode());
            //UMLEditor.currentMode = 6;
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

