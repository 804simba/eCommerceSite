package com.ecommerce.services.implementations;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.enums.Role;
import com.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    @Override
    public boolean addProduct(Product product, User user) {
        if (user.getRole() != Role.ADMIN) {
            return false;
        }
        try {
            byte[] imageData = product.getImage();
            InputStream inputStream = new ByteArrayInputStream(imageData);
            productDAO.addProduct(product.getProductName(),
                    product.getProductPrice(), product.getDescription(),
                    product.getCategory(), product.getQuantity(), inputStream);

        } catch (NullPointerException e) {
            System.out.println("Image is empty: " + e.getMessage());
        }
        return true;
    }
    @Override
    public Product getProductByID(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public boolean updateProduct(Product product, User user) {
        if (user.getRole() != Role.ADMIN) {
            return false;
        }
        InputStream imageStream = new ByteArrayInputStream(product.getBase64Image().getBytes());
        productDAO.updateProduct(product.getProductID(), product.getProductName(),
                imageStream, product.getProductPrice(), product.getDescription(),
                product.getCategory(), product.getQuantity());
        return true;
    }

    @Override
    public boolean deleteProduct(Product product, User user) {
        if (user.getRole() != Role.ADMIN) {
            return false;
        }
        productDAO.deleteProduct(product.getProductID());
        return true;
    }
    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
