package org.example.view;

import org.example.entity.User;
import org.example.exception.UserLoginException;
import org.example.exception.UserRegistrationException;
import org.example.exception.UserRegistrationIsPresentException;
import org.example.model.connection.ConnectionPool;
import org.example.model.connection.HikariCPConnectionPool;
import org.example.model.dao.UserDao;
import org.example.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationJFrame extends JFrame {
    private JPanel jPanel = new JPanel();
    private GridLayout gridLayout = new GridLayout(3,2,0,0);
    private JButton regButton = new JButton("Зарегистрироваться");
    private JButton mainMenuButton = new JButton("В главное меню");

    private JLabel loginLabel = new JLabel("Логин");
    private JLabel passwordLabel = new JLabel("Пароль");
    private JTextField loginTextField = new JTextField();
    private JTextField passwordTextField = new JTextField();
    private ConnectionPool connectionPool = HikariCPConnectionPool.getInstance();
    public RegistrationJFrame() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void init(){
        jPanel.setLayout(gridLayout);
        jPanel.add(loginLabel);
        jPanel.add(passwordLabel);
        jPanel.add(loginTextField);
        jPanel.add(passwordTextField);
        jPanel.add(mainMenuButton);
        jPanel.add(regButton);
        this.add(jPanel);
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String login = loginTextField.getText();
                String password = passwordTextField.getText();
                if(login==null || password==null){
                    JDialog jDialog = new JDialog();
                    JLabel jLabel = new JLabel("ОШИБКА");
                    jDialog.add(jLabel);
                    jDialog.setSize(200,200);
                    jDialog.setVisible(true);
                    return;
                }
                UserService userService = new UserService(new UserDao(connectionPool));
                try {
                    User user = userService.registerUserAsClient(login,password);
                    JDialog jDialog = new JDialog();
                    JLabel jLabel = new JLabel("РЕГИСТРАЦИЯ ПРОШЛА УСПЕШНО");
                    jDialog.add(jLabel);
                    jDialog.setSize(200,200);
                    jDialog.setVisible(true);
                    return;
                }catch (UserRegistrationIsPresentException e) {

                } catch (UserRegistrationException e) {

                }
            }
        });
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                dispose();
                new MainMenuJFrame().init();
            }
        });
    }
}
