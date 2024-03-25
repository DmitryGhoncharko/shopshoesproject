package org.example.view;

import org.example.entity.Product;
import org.example.exception.CannotFindAllProductsException;
import org.example.exception.CannotFindProductByIdException;
import org.example.model.connection.ConnectionPool;
import org.example.model.connection.HikariCPConnectionPool;
import org.example.model.dao.ProductDao;
import org.example.service.ProductService;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class ProductJFrame extends JFrame {
    private JPanel jPanel = new JPanel();
    private GridLayout gridLayout = new GridLayout(0,4,0,0);
    private ConnectionPool connectionPool = HikariCPConnectionPool.getInstance();

    public ProductJFrame() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void init(){
        jPanel.setLayout(gridLayout);
        ProductService productService = new ProductService(new ProductDao(connectionPool));
        try {
            List<Product> productList = productService.getAll();
            for(Product product : productList){
                JButton jButton = new JButton(product.getName());
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            Optional<Product> productOptional = productService.findById(product.getId());
                            if(productOptional.isPresent()){
                                setVisible(false);
                                dispose();
                                new SingleProductJFrame(productOptional.get()).init();
                            }
                        } catch (CannotFindProductByIdException e) {

                        }

                    }
                });
                jPanel.add(jButton);

            }
        } catch (CannotFindAllProductsException e) {
            throw new RuntimeException(e);
        }

        jPanel.setVisible(true);
        this.getContentPane().add(jPanel);

    }
}
