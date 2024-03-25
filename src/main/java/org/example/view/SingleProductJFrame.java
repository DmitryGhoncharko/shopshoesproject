package org.example.view;

import org.example.entity.Product;
import org.example.exception.CannotAddToBucketProductException;
import org.example.model.connection.ConnectionPool;
import org.example.model.connection.HikariCPConnectionPool;
import org.example.model.dao.ProductDao;
import org.example.model.dao.UserProductDao;
import org.example.service.UserProductService;
import org.example.util.Cache;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleProductJFrame extends JFrame {
    private Product product;
    private JPanel jPanel = new JPanel();
    private GridLayout gridLayout = new GridLayout(4,2,0,0);
    private ConnectionPool connectionPool = HikariCPConnectionPool.getInstance();
    public SingleProductJFrame(Product product) throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        this.setResizable(false);
        this.setVisible(true);
        this.product = product;
    }

    public void  init(){
        jPanel.setLayout(gridLayout);

        JLabel productName = new JLabel("Название " + product.getName());
        JLabel country = new JLabel("Страна " + product.getCountry());
        JLabel category = new JLabel("Категория " + product.getCategory());
        JLabel color = new JLabel("Цвет " +  product.getColor());
        JLabel material = new JLabel("Материал " + product.getMaterial());
        JLabel type = new JLabel("Тип" + product.getType());
        JLabel price = new JLabel("Цена " + product.getPrice());
        JLabel count = new JLabel("Колличество " + product.getCount());
        JLabel countUser = new JLabel("Кол-во для покупки");
        JTextField jTextField = new JTextField();
        JButton buy = new JButton("Добавить в корзину");
        JButton menu = new JButton("Товары");
        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int count = Integer.valueOf(jTextField.getText());
                UserProductDao userProductDao = new UserProductDao(connectionPool);
                ProductDao productDao = new ProductDao(connectionPool);
                UserProductService userProductService = new UserProductService(userProductDao,productDao);
                try {
                    userProductService.addToBucket(Cache.user.getId(), product.getId(),count);
                    JDialog jDialog = new JDialog();
                    JLabel jLabel = new JLabel("УСПЕШНО");
                    jDialog.add(jLabel);
                    jDialog.setSize(200,200);
                    jDialog.setVisible(true);
                    return;
                } catch (CannotAddToBucketProductException e) {

                }

            }
        });
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                dispose();
                new ProductJFrame().init();
            }
        });
        jPanel.add(productName);
        jPanel.add(country);
        jPanel.add(category);
        jPanel.add(color);
        jPanel.add(material);
        jPanel.add(type);
        jPanel.add(price);
        jPanel.add(count);
        jPanel.add(countUser);
        jPanel.add(jTextField);
        jPanel.add(buy);
        jPanel.add(menu);
        jPanel.setVisible(true);
        this.getContentPane().add(jPanel);
    }
}
