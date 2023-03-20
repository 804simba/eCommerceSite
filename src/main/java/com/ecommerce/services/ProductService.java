package com.ecommerce.services;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product product, User user);

    Product getProductByID(int id);
    boolean updateProduct(Product product, User user);
    boolean deleteProduct(Product product, User user);
    List<Product> getAllProducts();
}
