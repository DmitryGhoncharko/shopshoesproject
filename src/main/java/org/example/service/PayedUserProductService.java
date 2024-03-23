package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.entity.User;
import org.example.entity.UserProduct;
import org.example.exception.CannotPayUser;
import org.example.exception.DaoException;
import org.example.model.dao.PayedUserProductDao;
import org.example.model.dao.ProductDao;
import org.example.model.dao.UserDao;
import org.example.model.dao.UserProductDao;

import java.sql.Date;
import java.util.Optional;

@RequiredArgsConstructor
public class PayedUserProductService {
    private final PayedUserProductDao payedUserProductDao;
    private final UserProductDao userProductDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    public void create(long userProductId) throws CannotPayUser {
        try{
            Optional<UserProduct> userProductOptional = userProductDao.findById(userProductId);
            if(userProductOptional.isPresent()){
                UserProduct userProduct = userProductOptional.get();
                Optional<User> userOptional = userDao.findById(userProduct.getUser().getId());
                Optional<Product> productOptional = productDao.findById(userProduct.getProduct().getId());
                if(userOptional.isPresent() && productOptional.isPresent()){
                    User user = userOptional.get();
                    Product product = productOptional.get();
                    if(product.getCount()>=userProduct.getCount() && user.getBalance()>=userProduct.getFinalPrice()){
                        productDao.update(product.getId(),product.getName(),product.getCountry(),product.getCategory(),product.getColor(),product.getMaterial(),product.getType(),product.getPrice(),product.getDefaultPrice(),product.getCount()-userProduct.getCount());
                        userDao.updateUserBalance(userProduct.getUser().getId(),user.getBalance()-userProduct.getFinalPrice());
                        payedUserProductDao.create(userProductId,new Date(new java.util.Date().getTime()));
                    }
                }
            }
        }catch (DaoException e){
            throw new CannotPayUser();
        }
        throw new CannotPayUser();
    }

}
