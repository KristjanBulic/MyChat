package com.kristjanbulic;

import javax.swing.*;

public class Login {
    public JPanel panel1;
    private JTextField usernameTextField;
    private JButton joinButton;
    private JTextField IPTextField;
    private JTextField portTextField;

    public Login() { //collects ip, port and username and sends it to launcher
        joinButton.addActionListener(e -> {
            Launcher.startClient(IPTextField.getText(), Integer.parseInt(portTextField.getText()),
                    usernameTextField.getText());

        });
    }
}
