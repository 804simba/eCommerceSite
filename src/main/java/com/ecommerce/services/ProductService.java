package com.ecommerce.services;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

import java.util.List;

public interface ProductService {
    boolean addProduct(String productName, String productPrice, String imageName, String description, String categoryID, String quantity);
    Product getProductByID(int id);
    boolean editProduct(int productID, String productName, String imageName, String productPrice, String productDescription, String quantity);
    boolean deleteProduct(Product product);
    List<Product> getAllProducts();
}
