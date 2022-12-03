package com.company;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Sequence {
    private String symbols = "abcdefghijklmnoprstuvwqxyz        0123456789#$%&";
    private String sequence = "";
    private int sequenceLength;
    private Font sequenceFont;

    public Sequence(){
        File file = new File("src/res/matrix_code_nfi/matrix code nfi.otf");

        try {
            sequenceFont = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(Font.BOLD, 15f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(sequenceFont);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        changeSequence();
    }

    public char[] getSequenceInChars(){
        return sequence.toCharArray();
    }

    public String getSequence(){
        return sequence;
    }

    public Font getSequenceFont(){
        return sequenceFont;
    }

    public void setSequence(String sequence){
        this.sequence = sequence;
    }

    public int getSequenceLength(){
        return sequenceLength;
    }

    public void changeSequence(){
        Random random = new Random();

        sequence = "";

        for (int i = 0; i < new Random().nextInt(15); i++){
            sequence = sequence.concat(" ");
        }

        for (int i = 0; i < (MyFrame.SCREEN_HEIGHT / MyLabel.LABEL_WIDTH); i++){
            sequence = sequence.concat(String.valueOf(symbols.charAt(random.nextInt(symbols.length()))));
        }

        sequenceLength = getSequence().length();
    }

    public void changeChar(int i){
        char[] temp = getSequenceInChars();

        int index = new Random().nextInt(sequence.length() - i) + i;
        int index2 = new Random().nextInt(symbols.length());

        if (temp[index] != ' ' && symbols.charAt(index2) != ' ') temp[index] = symbols.charAt(index2);

        sequence = String.valueOf(temp);
    }
}
