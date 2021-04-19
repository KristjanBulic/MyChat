package com.kristjanbulic;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

public class Launcher {
    public static JFrame frame;
    public static void startClient(String ip, int port, String username){ //starts clients side of app
        try {
            Client usr = new Client(new Socket(ip, port), username);
            usr.start();
            frame = new JFrame("Server");
            frame.setSize(800, 400);
            frame.setContentPane(new SexyGui(usr).panel1);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                { //verify exit
                    int status = JOptionPane.showConfirmDialog(frame, "Sure?");
                    switch (status){
                        case 0:
                            //if user wants to exit code below execudes
                            usr.disconnect();
                            e.getWindow().dispose();
                            System.exit(0);

                        case 1:
                            break;

                        case 2:
                            break;
                    }

                }
            });
            frame.pack();
            frame.setVisible(true);
            Main.frame.dispose(); //destroys first windows for loging in
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Something went wrong");
        }
    }
}
