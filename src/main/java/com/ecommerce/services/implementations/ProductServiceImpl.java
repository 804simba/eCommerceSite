package com.ecommerce.services.implementations;

import com.ecommerce.dao.IProductDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final IProductDAO productDAO;
    @Override
    public boolean addProduct(String productName, String productPrice, String imageName, String description, String categoryID, String quantity) {
        return productDAO.addProduct(productName, productPrice, imageName, description, categoryID, quantity);
    }
    @Override
    public Product getProductByID(int id) {
        return productDAO.getProductById(id);
    }
    @Override
    public boolean editProduct(int productID, String productName, String imageName, String productPrice, String productDescription, String quantity) {
        return productDAO.editProduct(productID, productName, imageName, productPrice, productDescription, quantity);
    }
    @Override
    public boolean deleteProduct(int productID) {
        return productDAO.deleteProduct(productID);
    }
    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        return productDAO.getAllProducts();
    }
    @Override
    public List<Product> getListOfProductsHandler(String query) {
        return productDAO.getListOfProductsHandler(query);
    }
    @Override
    public Product getProductById(int productID) {
        return productDAO.getProductById(productID);
    }
    @Override
    public List<Product> get12ProductsOfPage(int index) {
        return productDAO.get12ProductsOfPage(index);
    }
    @Override
    public List<String> getAllProductImages() {
        return productDAO.getAllProductImages();
    }
}
