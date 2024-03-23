package org.example;

import org.example.exception.UserLoginException;
import org.example.model.connection.ConnectionPool;
import org.example.model.connection.HikariCPConnectionPool;
import org.example.model.dao.UserDao;
import org.example.service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static final ConnectionPool connectionPool = HikariCPConnectionPool.getInstance();
    private static JFrame frame;
    private static UserService userService;
    private static JLabel loginLabel;
    private static JLabel passwordLabel;
    private static JTextField loginTextField;
    private static JTextField passwordTextField;
    private static JButton loginButton;
    private static void initService(){
        UserDao userDao = new UserDao(connectionPool);
        userService = new UserService(userDao);

    }
    private static void init(){
        loginLabel = new JLabel("Логин");
        passwordLabel = new JLabel("Пароль");
        loginTextField = new JTextField();
        passwordTextField = new JTextField();
        loginButton = new JButton("Войти");
        loginLabel.setBounds(430,80,100,50);
        passwordLabel.setBounds(630,80,100,50);
        loginTextField.setBounds(400,120,100,50);
        passwordTextField.setBounds(600,120,100,50);
        loginButton.setBounds(300,50,100,100);
        loginLabel.setVisible(true);
        passwordLabel.setVisible(true);
        loginTextField.setVisible(true);
        passwordTextField.setVisible(true);
        loginButton.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               String login = loginTextField.getText();
               String password = passwordTextField.getText();
                try {
                    userService.loginUser(login,password);
                } catch (UserLoginException e) {
                    JDialog jDialog = new JDialog(frame,"ОШИБКА");
                    JLabel jLabel = new JLabel("ОШИБКА");
                    jDialog.add(jLabel);
                    jDialog.setSize(200,200);
                    jDialog.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    private static void createAndShowGUI() {
        initService();
        init();
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.getContentPane().add(loginLabel);
        frame.getContentPane().add(passwordLabel);
        frame.getContentPane().add(loginTextField);
        frame.getContentPane().add(passwordTextField);
        frame.getContentPane().add(loginButton);
        frame.setVisible(true);
    }
}

/**
 * create table buyer
 * (
 *     Buyer_id        int         not null
 *         primary key,
 *     Buyer_name      varchar(45) not null,
 *     Buyer_last      varchar(20) not null,
 *     Buyer_first     varchar(20) not null,
 *     Buyer_second    varchar(20) not null,
 *     Buyer_city      varchar(20) not null,
 *     Buyer_street    varchar(20) not null,
 *     Buyer_building  int         not null,
 *     Buyer_telephone int         not null,
 *     Buyer_mail      varchar(45) not null
 * );
 *
 * create index buyindex
 *     on buyer (Buyer_id);
 *
 * grant references (Buyer_id) on table buyer to buch@localhost;
 *
 * create table product
 * (
 *     Product_id       int         not null
 *         primary key,
 *     Product_name     varchar(50) not null,
 *     Product_country  varchar(30) not null,
 *     Product_type     char        not null,
 *     Product_category varchar(15) not null,
 *     Product_color    varchar(15) not null,
 *     Product_material varchar(20) not null,
 *     constraint check_Product_category
 *         check (`Product_category` in (_utf8mb4\'Ð·Ð¸Ð¼Ð°\',_utf8mb4\'Ð²ÐµÑÐ½Ð°-Ð¾ÑÐµÐ½Ñ\',_utf8mb4\'Ð»ÐµÑÐ¾\',_utf8mb4\'Ð´ÑÑÐ³Ð¾Ðµ\')),
 * 	constraint check_Product_type
 * 		check (`Product_type` in (_utf8mb4\'Ð\',_utf8mb4\'Ð\',_utf8mb4\'Ð\'))
 * );
 *
 * create index Prodindex
 *     on product (Product_id);
 *
 * grant references (Product_id) on table product to buch@localhost;
 *
 * create table provider
 * (
 *     Provider_id        int         not null
 *         primary key,
 *     Provider_name      varchar(45) not null,
 *     Provider_last      varchar(20) not null,
 *     Provider_first     varchar(20) not null,
 *     Provider_second    varchar(20) not null,
 *     Provider_city      varchar(20) not null,
 *     Provider_street    varchar(20) not null,
 *     Provider_building  int         not null,
 *     Provider_telephone int         not null,
 *     Provider_mail      varchar(45) not null
 * );
 *
 * create index provr_index
 *     on provider (Provider_id);
 *
 * grant references (Provider_id) on table provider to buch@localhost;
 *
 * create table sale
 * (
 *     Sale_id   int  not null
 *         primary key,
 *     Buyer_id  int  not null,
 *     Sale_data date null,
 *     constraint sale_ibfk_1
 *         foreign key (Buyer_id) references buyer (Buyer_id)
 *             on delete cascade
 * );
 *
 * create table info_sale
 * (
 *     Sale_id    int            not null,
 *     Product_id int            not null,
 *     Sale_kol   int            not null,
 *     Sale_price decimal(7, 2)  not null,
 *     Sale_sum   decimal(10, 2) not null,
 *     primary key (Sale_id, Product_id),
 *     constraint info_sale_ibfk_1
 *         foreign key (Sale_id) references sale (Sale_id)
 *             on delete cascade,
 *     constraint info_sale_ibfk_2
 *         foreign key (Product_id) references product (Product_id)
 *             on delete cascade
 * );
 *
 * create index Product_id
 *     on info_sale (Product_id);
 *
 * grant insert, update on table info_sale to buch@localhost;
 *
 * create index Buyer_id
 *     on sale (Buyer_id);
 *
 * grant insert, update on table sale to buch@localhost;
 *
 * create table supply
 * (
 *     Supply_id   int  not null
 *         primary key,
 *     Provider_id int  not null,
 *     Supply_data date null,
 *     constraint supply_ibfk_1
 *         foreign key (Provider_id) references provider (Provider_id)
 *             on delete cascade
 * );
 *
 * create table info_supply
 * (
 *     Supply_id    int            not null,
 *     Product_id   int            not null,
 *     Supply_kol   int            not null,
 *     Supply_price decimal(7, 2)  not null,
 *     Supply_sum   decimal(10, 2) not null,
 *     primary key (Supply_id, Product_id),
 *     constraint info_supply_ibfk_1
 *         foreign key (Supply_id) references supply (Supply_id)
 *             on delete cascade,
 *     constraint info_supply_ibfk_2
 *         foreign key (Product_id) references product (Product_id)
 *             on delete cascade
 * );
 *
 * create index Product_id
 *     on info_supply (Product_id);
 *
 * grant insert, update on table info_supply to buch@localhost;
 *
 * create index Provider_id
 *     on supply (Provider_id);
 *
 * grant insert, update on table supply to buch@localhost;
 */