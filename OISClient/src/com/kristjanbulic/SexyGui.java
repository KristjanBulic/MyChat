package com.kristjanbulic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SexyGui extends Container {
    private JButton button1;
    public JPanel panel1;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton exitButton;
    private final Client client;


    public SexyGui(Client client) {

        this.client = client;

        button1.addActionListener(e -> {
            client.sendMessage(textField1.getText());
            textField1.setText("");
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



        (new Thread(() -> {
            while (true) {
                    if (client.unRead.size() != 0) {
                        addChat(client.unRead.firstElement());
                        textArea1.setCaretPosition(textArea1.getDocument().getLength());
                    }
                }

        })).start();
        exitButton.addActionListener(e -> {
            Launcher.frame.dispose();
            client.disconnect();
            System.exit(0);
        });
    }
    public void addChat(String mes){
        textArea1.append(mes + "\n");
        client.unRead.remove(mes);
    }
}
