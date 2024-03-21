package org.example.model.dao;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.entity.UserProduct;
import org.example.exception.DaoException;
import org.example.model.connection.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserProductDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_CREATE_PRODUCT = "insert into _user_product(user_id, product_id, product_count, product_final_price) values (?,?,?,?)";
    private static final String SQL_UPDATE_PRODUCT = "update _user_product set user_id = ?, product_id = ?, product_count = ?, product_final_price = ? where user_product_id = ?";
    private static final String SQL_DELETE_PRODUCT = "delete  from _user_product where user_product_id = ?";

    private static final String SQL_FIND_BY_ID = "select  u.user_id, u.user_login, u.user_password, u.user_role_id, u.user_balance , p.product_id, p.product_name, p.product_manufacture_country, p.product_category ,p.product_color, p.product_material, p.product_type, p.product_price, p.product_default_price, p.product_count, product_count, product_final_price from _user_product\n" +
            "left join shopshoes._user u on u.user_id = _user_product.user_id\n" +
            "left join shopshoes._product p on p.product_id = _user_product.product_id\n" +
            "where user_product_id = ?\n";

    private static final String SQL_FIND_ALL = "select  u.user_id, u.user_login, u.user_password, u.user_role_id, u.user_balance , p.product_id, p.product_name, p.product_manufacture_country, p.product_category ,p.product_color, p.product_material, p.product_type, p.product_price, p.product_default_price, p.product_count, product_count, product_final_price from _user_product\n" +
            "left join shopshoes._user u on u.user_id = _user_product.user_id\n" +
            "left join shopshoes._product p on p.product_id = _user_product.product_id\n";
    public void create(long userId, long productId, long productCount, double finalPrice) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_PRODUCT)){
            preparedStatement.setLong(1,userId);
            preparedStatement.setLong(2,productId);
            preparedStatement.setLong(3,productCount);
            preparedStatement.setDouble(4,finalPrice);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
    public void update(long id,long userId, long productId, long productCount, double finalPrice) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT)){
            preparedStatement.setLong(1,userId);
            preparedStatement.setLong(2,productId);
            preparedStatement.setLong(3,productCount);
            preparedStatement.setDouble(4,finalPrice);
            preparedStatement.setLong(5,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
    public void deleteById(long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
    public Optional<UserProduct> findById(long id){
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
              return Optional.of(
                      UserProduct.builder().
                              id(resultSet.getLong(1)).
                              user(
                                      User.builder().
                                              id(resultSet.getLong(2)).
                                              login(resultSet.getString(3)).
                                              password(resultSet.getString(4)).
                                              role(Role.values()[resultSet.getInt(5)]).
                                              balance(resultSet.getDouble(6)).
                                              build()
                              ).
                              product(
                                      Product.builder().
                                              id(resultSet.getLong(7)).
                                              name(resultSet.getString(8)).
                                              country(resultSet.getString(9)).
                                              category(resultSet.getString(10)).
                                              color(resultSet.getString(11)).
                                              material(resultSet.getString(12)).
                                              type(resultSet.getString(13)).
                                              price(resultSet.getDouble(14)).
                                              defaultPrice(resultSet.getDouble(15)).
                                              count(resultSet.getInt(16)).
                                              build()
                              ).
                              count(resultSet.getInt(17)).
                              finalPrice(resultSet.getDouble(18)).
                              build()
              );
            }
        }catch (SQLException e){

        }
       return Optional.empty();
    }
    public List<UserProduct> findAll(){
        List<UserProduct> userProductList = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet  = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()){
                userProductList.add(
                        UserProduct.builder().
                                id(resultSet.getLong(1)).
                                user(
                                        User.builder().
                                                id(resultSet.getLong(2)).
                                                login(resultSet.getString(3)).
                                                password(resultSet.getString(4)).
                                                role(Role.values()[resultSet.getInt(5)]).
                                                balance(resultSet.getDouble(6)).
                                                build()
                                ).
                                product(
                                        Product.builder().
                                                id(resultSet.getLong(7)).
                                                name(resultSet.getString(8)).
                                                country(resultSet.getString(9)).
                                                category(resultSet.getString(10)).
                                                color(resultSet.getString(11)).
                                                material(resultSet.getString(12)).
                                                type(resultSet.getString(13)).
                                                price(resultSet.getDouble(14)).
                                                defaultPrice(resultSet.getDouble(15)).
                                                count(resultSet.getInt(16)).
                                                build()
                                ).
                                count(resultSet.getInt(17)).
                                finalPrice(resultSet.getDouble(18)).
                                build()
                );
            }
        }catch (SQLException e){

        }
        return userProductList;
    }
}
