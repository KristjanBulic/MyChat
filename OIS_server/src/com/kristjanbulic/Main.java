package com.kristjanbulic;

import javax.swing.*;


public class Main  {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Start server");
        frame.setSize(800, 400);
        frame.setContentPane(new ServerGUI().panel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
