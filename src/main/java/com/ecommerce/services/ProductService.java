package com.ecommerce.services;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

import java.util.List;

public interface ProductService {
    boolean addProduct(Product product);

    Product getProductByID(int id);
    boolean editProduct(Product product);
    boolean deleteProduct(Product product);
    List<Product> getAllProducts();
}
