package com.company;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel{
    public MyPanel(){
        this.setBackground(Color.BLACK);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        for (int i = 0; i < MyFrame.SCREEN_WIDTH / MyLabel.LABEL_WIDTH; i ++){
            this.add(new MyLabel());
        }
    }
}
