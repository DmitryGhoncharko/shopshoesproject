package org.example.model.dao;

import lombok.RequiredArgsConstructor;
import org.example.dto.PayedUserProductDto;
import org.example.exception.DaoException;
import org.example.model.connection.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PayedUserProductDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_CREATE = "insert into payed_user_product(user_product_id, payed_date) VALUES (?,?)";
    private static final String SQL_UPDATE = "update payed_user_product set user_product_id = ?, payed_date = ? where payed_user_product_id = ?";
    private static final String SQL_DELETE = "delete from payed_user_product where payed_user_product_id = ?";
    private static final String SQL_FIND_BY_ID = "select payed_user_product_id, user_product_id, payed_date from payed_user_product where payed_user_product_id = ?";
    private static final String SQL_FIND_ALL = "select payed_user_product_id, user_product_id, payed_date from payed_user_product";
    public PayedUserProductDto create(long userProductId, Date payedDate) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setLong(1,userProductId);
            preparedStatement.setDate(2,payedDate);
            int rowsCreated = preparedStatement.executeUpdate();
            if(rowsCreated>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if(resultSet.next()){
                    return PayedUserProductDto.builder().
                            id(resultSet.getLong(1)).
                            userProductId(userProductId).
                            payedDate(payedDate).
                            build();
                }
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        throw new DaoException();
    }
    public void update(long id, long userProductId, Date payedDate) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setLong(1,userProductId);
            preparedStatement.setDate(2,payedDate);
            preparedStatement.setLong(3,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
    public void delete(long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)){
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (SQLException e){
            throw new DaoException(e);
        }
    }
    public Optional<PayedUserProductDto> findById(long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(
                        PayedUserProductDto.builder().
                                id(resultSet.getLong(1)).
                                userProductId(resultSet.getLong(2)).
                                payedDate(resultSet.getDate(3)).
                                build()
                );
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return Optional.empty();
    }
    public List<PayedUserProductDto> findAll() throws DaoException {
        List<PayedUserProductDto> payedUserProductDtos = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
            while (resultSet.next()){
                payedUserProductDtos.add(
                        PayedUserProductDto.builder().
                                id(resultSet.getLong(1)).
                                userProductId(resultSet.getLong(2)).
                                payedDate(resultSet.getDate(3)).
                                build()
                );
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return payedUserProductDtos;
    }
}
