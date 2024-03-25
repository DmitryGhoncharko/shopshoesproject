package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.entity.UserProduct;
import org.example.exception.CannotAddToBucketProductException;
import org.example.exception.CannotFindUserBucketProductsByUserIdException;
import org.example.exception.CannotRemoveFromBucketException;
import org.example.exception.DaoException;
import org.example.model.dao.ProductDao;
import org.example.model.dao.UserProductDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserProductService {
    private final UserProductDao userProductDao;
    private final ProductDao productDao;
    public void addToBucket(long userId, long productId, int count) throws CannotAddToBucketProductException {
        try{
           Optional<Product> optionalProduct =  productDao.findById(productId);
           if(optionalProduct.isPresent()){
               Product product = optionalProduct.get();
               double finalPrice = Math.round(product.getPrice() * count);
               userProductDao.create(userId,productId,count,finalPrice);
           }
        }catch (DaoException e){
            throw new CannotAddToBucketProductException();
        }
    }
    public void removeFromBucket(long id) throws  CannotRemoveFromBucketException {
        try{
            userProductDao.deleteById(id);
        }catch (DaoException e){
            throw new CannotRemoveFromBucketException();
        }
    }
    public List<UserProduct> getByUserId(long userId) throws CannotFindUserBucketProductsByUserIdException {
        try{
            return  userProductDao.findAll().stream().filter(userProduct -> userProduct.getUser().getId().equals(userId)).collect(Collectors.toList());
        }catch (DaoException e){
            throw new CannotFindUserBucketProductsByUserIdException();
        }
    }
}
