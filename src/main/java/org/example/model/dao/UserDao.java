package org.example.model.dao;

import lombok.RequiredArgsConstructor;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.exception.DaoException;
import org.example.model.connection.ConnectionPool;

import java.sql.*;
import java.util.Optional;

@RequiredArgsConstructor
public class UserDao {
    private final ConnectionPool connectionPool;
    private static final String SQL_CREATE_USER = "insert into _user(user_login, user_password, user_role_id) values (?,?,?)";
    private static final String SQL_FIND_USER_BY_ID = "select user_login, user_password, user_role_id, user_balance from _user where user_id = ?";
    public User create(String login, String password, Role role) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.setLong(3,role.ordinal());
            int countRowsUpdated = preparedStatement.executeUpdate();
            if(countRowsUpdated>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                return User.
                        builder().
                        id(resultSet.getLong(1)).
                        login(login).
                        password(password).
                        role(role).
                        build();
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        throw new DaoException();
    }
    public Optional<User> findById(Long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(
                        User.builder().
                                id(id).
                                login(resultSet.getString(1)).
                                password(resultSet.getString(2)).
                                role(Role.values()[resultSet.getInt(3)]).
                                balance(resultSet.getDouble(4)).
                                build()
                );
            }
        }catch (SQLException e){
            throw new DaoException(e);
        }
        return Optional.empty();
    }
}
