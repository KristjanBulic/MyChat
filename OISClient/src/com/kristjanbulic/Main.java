package com.kristjanbulic;


import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            Client usr = new Client(new Socket("127.0.0.1", 5000));
            usr.start();
            JFrame frame = new JFrame("Fucking Shit");
            frame.setSize(800, 400);
            frame.setContentPane(new SexyGui(usr).panel1);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
