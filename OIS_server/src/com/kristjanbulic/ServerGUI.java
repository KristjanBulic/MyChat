package com.kristjanbulic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerGUI {
    Server ser;
    public JPanel panel1;
    private JTextField portTextField;
    private JButton startServerButton;
    private JButton stopServerButton;


    public ServerGUI() {

        startServerButton.addActionListener(e -> {
            try {
                ServerSocket s = new ServerSocket(Integer.parseInt(portTextField.getText())); //opens new server on port xxxx
                ser = new Server(s);
                ser.start();
                startServerButton.setVisible(false);
                startServerButton.setText("Running");
                stopServerButton.setVisible(true);
            } catch (IOException a) {
                javax.swing.JOptionPane.showMessageDialog(panel1, "Sorry, something went wrong");
                a.printStackTrace();
            }
        });

        stopServerButton.addActionListener(e -> {
            ser.interrupt();  //stops server thread
            ser.stopServer();
            stopServerButton.setVisible(false);
            startServerButton.setText("Start");
            startServerButton.setVisible(true);
        });
    }
}
