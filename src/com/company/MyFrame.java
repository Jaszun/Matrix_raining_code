package com.company;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 500;

    public MyFrame(){
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setIconImage(new ImageIcon("src/res/icon.png").getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Matrix");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().add(new MyPanel());
    }
}
