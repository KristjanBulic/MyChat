package com.kristjanbulic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerGUI {
    public JPanel panel1;
    private JTextField portTextField;
    private JButton startServerButton;


    public ServerGUI() {

        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ServerSocket s = new ServerSocket(Integer.parseInt(portTextField.getText()));
                    Server ser = new Server(s);
                    ser.start();
                    startServerButton.removeActionListener(this);
                    startServerButton.setText("Running");
                } catch (IOException a) {
                    javax.swing.JOptionPane.showMessageDialog(panel1, "Sorry, something went wrong");
                    a.printStackTrace();
                }
            }
        });
    }
}
