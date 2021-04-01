package com.kristjanbulic;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Launcher {
    public static JFrame frame;
    public static void startClient(String ip, int port, String username){
        try {
            Client usr = new Client(new Socket(ip, port), username);
            usr.start();
            frame = new JFrame("Server");
            frame.setSize(800, 400);
            frame.setContentPane(new SexyGui(usr).panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            Main.frame.dispose();
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Something went wrong");
        }
    }
}
