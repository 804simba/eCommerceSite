package com.ecommerce.dao;

import com.ecommerce.entity.Product;
import com.ecommerce.exceptions.ProductNotFoundException;

import java.sql.ResultSet;
import java.util.List;

public interface IProductDAO {
    List<Product> getAllProducts() throws ProductNotFoundException;
    Product getProductById(int productID);
    boolean addProduct(String productName, String productPrice, String imageName, String description, String categoryID, String quantity);
    boolean editProduct(int productID, String productName, String imageName, String productPrice, String productDescription, String quantity);
    boolean deleteProduct(int productId);
    List<Product> getListOfProductsHandler(String query);
    public List<Product> get12ProductsOfPage(int index);
    public List<String> getAllProductImages();
}
