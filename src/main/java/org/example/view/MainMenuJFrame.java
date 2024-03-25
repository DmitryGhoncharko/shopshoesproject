package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuJFrame extends JFrame {
    private JButton loginButton = new JButton("Войти");
    private JButton registrationButton = new JButton("Регистрация");

    public MainMenuJFrame() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        this.setVisible(true);
    }

    public void init(){
        JPanel jPanel = new JPanel();
        loginButton.setBounds(400,400,200,100);
        registrationButton.setBounds(400,200,200,100);
        loginButton.setVisible(true);
        registrationButton.setVisible(true);
        jPanel.add(loginButton);
        jPanel.add(registrationButton);
        jPanel.setVisible(true);
        this.getContentPane().add(jPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                dispose();
                new LoginJFrame().init();
            }
        });
        registrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                dispose();
                new RegistrationJFrame().init();
            }
        });
    }
}
