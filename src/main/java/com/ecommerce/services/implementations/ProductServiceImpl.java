package com.ecommerce.services.implementations;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.enums.Role;
import com.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    @Override
    public boolean createProduct(Product product, User user) {
        if (user.getRole() != Role.ADMIN) {
            return false;
        }
        productDAO.addProduct(product);
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
        productDAO.updateProduct(product);
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
