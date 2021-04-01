package com.kristjanbulic;

import javax.swing.*;

public class Login {
    public JPanel panel1;
    private JTextField usernameTextField;
    private JButton joinButton;
    private JTextField IPTextField;
    private JTextField portTextField;
    private JPasswordField passwordPasswordField;
    private JButton singUPButton;

    public Login() {
        joinButton.addActionListener(e -> {
            Launcher.startClient(IPTextField.getText(), Integer.parseInt(portTextField.getText()),
                    usernameTextField.getText(), passwordPasswordField.getPassword().toString(), false);

        });
        singUPButton.addActionListener(e -> {
            Launcher.startClient(IPTextField.getText(), Integer.parseInt(portTextField.getText()),
                    usernameTextField.getText(), passwordPasswordField.getPassword().toString(), true);

        });
    }
}
