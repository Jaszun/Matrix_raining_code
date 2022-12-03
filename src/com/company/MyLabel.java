package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

public class MyLabel extends JLabel implements Runnable{
    public static final int LABEL_WIDTH = 15;
    private Sequence sequence;
    private Thread thread;
    private String threadName;
    private int sequenceLen = 0;
    private String display = "<html>";
    private String hideSequence = "<html>";
    private int speed;

    public MyLabel(){
        sequence = new Sequence();
        speed = new Random().nextInt(100) + 100;

        byte[] array = new byte[7];
        new Random().nextBytes(array);
        threadName = new String(array, Charset.forName("UTF-8"));

        thread = new Thread(this, threadName);
        thread.start();

        this.setPreferredSize(new Dimension(LABEL_WIDTH, MyFrame.SCREEN_HEIGHT));
        this.setVerticalAlignment(SwingConstants.TOP);
        this.setFont(sequence.getSequenceFont());
//        this.setForeground(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
        this.setForeground(Color.GREEN);

//        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                setForeground(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
//            }
//        });
    }



    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(7000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                displaySequence();
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void displaySequence(){
        if (sequenceLen < sequence.getSequence().length()){
            for (int i = 0; i < sequenceLen; i++){
                display = display.concat(sequence.getSequence().charAt(i) + " <br/>");
                if (i % 2 == 0) sequence.changeChar(0);
            }

            this.setText(display + "</html>");

            display = "<html>";
            sequenceLen++;
        }

        if (sequenceLen == sequence.getSequence().length()){
//            --- pauza po narysowaniu ---
//
//            for (int i = 0; i < new Random().nextInt(10) + 5; i++){
//                try {
//                    Thread.sleep(200);
//                    sequence.changeChar();
//                    for (int j = 0; j < sequenceLen - 1; j++){
//                        display = display.concat(sequence.getSequence().charAt(j) + " <br/>");
//                        if (j % 2 == 0) sequence.changeChar();
//                    }
//
//                    this.setText(display + "</html>");
//
//                    display = "<html>";
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }

            char[] hideDisplay;

            for (int j = 0; j < sequence.getSequenceLength(); j++){
                try {
                    Thread.sleep(speed);

                    sequence.changeChar(j);
                    hideDisplay = sequence.getSequenceInChars();

                    hideDisplay[j] = ' ';

                    sequence.setSequence(String.valueOf(hideDisplay));

                    for (int i = 0; i < sequence.getSequenceLength(); i++){
                        hideSequence = hideSequence.concat(sequence.getSequence().charAt(i) + " <br/>");
                    }

                    this.setText(hideSequence + "</html>");

                    hideSequence = "<html>";

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            sequence.changeSequence();
            sequenceLen = 0;
            display = "<html>";
            speed = new Random().nextInt(100) + 100;
//            this.setForeground(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
        }
    }
}
