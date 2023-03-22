package com.ecommerce.services.implementations;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
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
    public boolean deleteProduct(Product product) {
        return productDAO.deleteProduct(product.getProductID());
    }
    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
