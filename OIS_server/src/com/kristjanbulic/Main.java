package com.kristjanbulic;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Main  {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Start server");
        frame.setSize(800, 400);
        frame.setContentPane(new ServerGUI().panel1);

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int status = JOptionPane.showConfirmDialog(frame, "Make sure server is closed");
                switch (status){
                    case 0:
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
    }
}
