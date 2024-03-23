package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.exception.*;
import org.example.model.dao.ProductDao;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;

    public Product addProduct(String name, String country, String category, String color, String material, String type, double price, double defaultPrice, int count) throws ProductInvalidDataException, CannotAddProductException {
        if (name == null || country == null || category == null || color == null || material == null || type == null ||
                name.isEmpty() || name.isBlank() || country.isBlank() || country.isEmpty() || category.isEmpty() || category.isBlank() ||
                color.isBlank() || color.isEmpty() || material.isEmpty() || material.isBlank() || type.isEmpty() || type.isBlank()) {
                throw new ProductInvalidDataException();
        }
        try {
          return   productDao.create(name,country,category,color,material,type,price,defaultPrice,count);
        } catch (DaoException e) {
            throw new CannotAddProductException(e);
        }
    }
    public void deleteProductById(long id) throws CannotDeleteProductException {
        try {
            productDao.deleteById(id);
        } catch (DaoException e) {
            throw new CannotDeleteProductException(e);
        }
    }
    public Product updateProduct(long id,String name, String country, String category, String color, String material, String type, double price, double defaultPrice, int count) throws ProductInvalidDataException, CannotUpdateProductException {
        if (name == null || country == null || category == null || color == null || material == null || type == null ||
                name.isEmpty() || name.isBlank() || country.isBlank() || country.isEmpty() || category.isEmpty() || category.isBlank() ||
                color.isBlank() || color.isEmpty() || material.isEmpty() || material.isBlank() || type.isEmpty() || type.isBlank()) {
            throw new ProductInvalidDataException();
        }

        try {
            return productDao.update(id,name,country,category,color,material,type,price,defaultPrice,count);
        } catch (DaoException e) {
            throw new CannotUpdateProductException(e);
        }
    }
    public List<Product> getAll() throws CannotFindAllProductsException {
        try {
           return productDao.findAll();
        } catch (DaoException e) {
            throw new CannotFindAllProductsException();
        }
    }
    public Optional<Product> findById(long id) throws CannotFindProductByIdException {
        try{
            return productDao.findById(id);
        }catch (DaoException e){
            throw new CannotFindProductByIdException();
        }
    }

}