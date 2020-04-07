package editor_main;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String imgName) {
        this.setIcon(new ImageIcon(getClass().getResource("../Image/"+imgName)));
        //this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setPreferredSize(new Dimension(80, 80));

    }


}
