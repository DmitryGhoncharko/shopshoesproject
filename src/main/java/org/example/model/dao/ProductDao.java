package org.example.model.dao;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.exception.DaoException;
import org.example.model.connection.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_CREATE_PRODUCT = "insert into _product(product_name, product_manufacture_country, product_category, product_color, product_material, product_type, product_price, product_default_price, product_count) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_PRODUCT = "update _product set product_name = ?, product_manufacture_country = ?, product_category = ?, product_color = ?, product_material = ?,product_type = ?, product_price = ?, product_default_price = ?, product_count = ? where product_id = ?";
    private static final String SQL_DELETE_PRODUCT_BY_ID = "delete  from  _product where product_id = ?";
    private static final String SQL_FIND_ALL = "select  product_id, product_name, product_manufacture_country, product_category, product_color, product_material, product_type, product_price, product_default_price, product_count from _product";
    private static final String SQL_FIND_BY_ID = "select  product_id, product_name, product_manufacture_country, product_category, product_color, product_material, product_type, product_price, product_default_price, product_count from _product where product_id = ?";
    public Product create(String name, String manufactureCountry, String category, String color, String material, String type, double price,double defaultPrice, int count) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,manufactureCountry);
            preparedStatement.setString(3,category);
            preparedStatement.setString(4,color);
            preparedStatement.setString(5,material);
            preparedStatement.setString(6,type);
            preparedStatement.setDouble(7,price);
            preparedStatement.setDouble(8,defaultPrice);
            preparedStatement.setInt(9,count);
            int countRowsCreated = preparedStatement.executeUpdate();
            if(countRowsCreated>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    return Product.builder().
                            id(resultSet.getLong(1)).
                            name(name).
                            country(manufactureCountry).
                            category(category).
                            color(color).
                            material(material).
                            type(type).
                            price(price).
                            defaultPrice(defaultPrice).
                            count(count).
                            build();
                }
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        throw new DaoException();
    }
    public Product update(long id,String name, String manufactureCountry, String category, String color, String material, String type, double price,double defaultPrice, int count) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,manufactureCountry);
            preparedStatement.setString(3,category);
            preparedStatement.setString(4,color);
            preparedStatement.setString(5,material);
            preparedStatement.setString(6,type);
            preparedStatement.setDouble(7,price);
            preparedStatement.setDouble(8,defaultPrice);
            preparedStatement.setInt(9,count);
            preparedStatement.setLong(10,id);
            int countRowsCreated = preparedStatement.executeUpdate();
            if(countRowsCreated>0){
                    return Product.builder().
                            id(id).
                            name(name).
                            country(manufactureCountry).
                            category(category).
                            color(color).
                            material(material).
                            type(type).
                            price(price).
                            defaultPrice(defaultPrice).
                            count(count).
                            build();

            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        throw new DaoException();
    }
    public void deleteById(long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT_BY_ID)){
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
    public List<Product> findAll() throws DaoException {
        List<Product> productList = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()){
                productList.add(
                        Product.builder().
                                id(resultSet.getLong(1)).
                                name(resultSet.getString(2)).
                                country(resultSet.getString(3)).
                                category(resultSet.getString(4)).
                                color(resultSet.getString(5)).
                                material(resultSet.getString(6)).
                                type(resultSet.getString(7)).
                                price(resultSet.getDouble(8)).
                                defaultPrice(resultSet.getDouble(9)).
                                count(resultSet.getInt(10)).
                                build()
                );
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return productList;
    }
    public Optional<Product> findById(long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(
                        Product.builder().
                                id(resultSet.getLong(1)).
                                name(resultSet.getString(2)).
                                country(resultSet.getString(3)).
                                category(resultSet.getString(4)).
                                color(resultSet.getString(5)).
                                material(resultSet.getString(6)).
                                type(resultSet.getString(7)).
                                price(resultSet.getDouble(8)).
                                defaultPrice(resultSet.getDouble(9)).
                                count(resultSet.getInt(10)).
                                build()
                );
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return Optional.empty();
    }
}
