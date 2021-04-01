package com.kristjanbulic;


import javax.swing.*;

public class Main {
    public static JFrame frame;
    public static void main(String[] args) {
            frame = new JFrame("Login");
            frame.setSize(400, 400);
            frame.setContentPane(new Login().panel1);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

    }

}
