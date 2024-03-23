package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.exception.DaoException;
import org.example.exception.UserLoginException;
import org.example.exception.UserRegistrationException;
import org.example.exception.UserRegistrationIsPresentException;
import org.example.model.dao.UserDao;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public User registerUserAsClient(String login, String password) throws UserRegistrationException, UserRegistrationIsPresentException {
        if(login==null || password == null || login.isBlank() || login.isEmpty() || password.isBlank() || password.isEmpty()){
            throw new UserRegistrationException();
        }
        try{
            Optional<User> userOptional = userDao.findByLogin(login);
            if(userOptional.isPresent()){
                throw new UserRegistrationIsPresentException();
            }
           return userDao.create(login,password, Role.CLIENT);
        }catch (DaoException e){
            throw new UserRegistrationException();
        }
    }
    public User loginUser(String login, String password) throws UserLoginException {
        if(login==null || password == null || login.isBlank() || login.isEmpty() || password.isBlank() || password.isEmpty()){
            throw new UserLoginException();
        }
         try{
             Optional<User> userOptional = userDao.findByLogin(login);
             if(userOptional.isPresent()){
                User user = userOptional.get();
                if(user.getLogin().equals(login) && user.getPassword().equals(password)){
                    return user;
                }
                throw new UserLoginException();
             }
         }catch (DaoException e){
             throw new UserLoginException();
         }
        throw new UserLoginException();
    }
}
