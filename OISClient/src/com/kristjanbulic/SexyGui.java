package com.kristjanbulic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SexyGui extends Container {
    private JButton button1;
    public JPanel panel1;
    private JTextField textField1;
    private JTextArea textArea1;

    Client client;


    public SexyGui(Client client) {

        this.client = client;

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessage(textField1.getText());
                textField1.setText("");
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10){
                    client.sendMessage(textField1.getText());
                    textField1.setText("");
                }
            }
        });

        (new Thread(){
            @Override
            public void run() {
                while (true) {
                    synchronized (client.getNewChat()) {
                        if (client.isNewChat()) {
                            textArea1.append(client.getLastChat() + "\n");
                            client.setNewChat(false);
                        }
                    }
                }
            }
        }).start();
    }
}
