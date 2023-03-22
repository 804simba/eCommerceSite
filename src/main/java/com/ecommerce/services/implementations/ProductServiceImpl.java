package com.ecommerce.services.implementations;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;
    @Override
    public boolean addProduct(Product product) {
        boolean successful = false;
        try {
//            byte[] imageData = product.getImage();
//            InputStream inputStream = new ByteArrayInputStream(imageData);
            successful = productDAO.addProduct(product.getProductName(),
                    product.getProductPrice(), product.getImageName(), product.getDescription(),
                    String.valueOf(product.getCategory().getId()), String.valueOf(product.getQuantity()));

        } catch (NullPointerException e) {
            System.out.println("Image is empty: " + e.getMessage());
        }
        return successful;
    }
    @Override
    public Product getProductByID(int id) {
        return productDAO.getProductById(id);
    }

    @Override
    public boolean editProduct(Product product) {
//        InputStream imageStream = new ByteArrayInputStream(product.getBase64Image().getBytes());
//        return productDAO.editProduct(product.getProductID(), product.getProductName(),
//                imageStream, product.getProductPrice(), product.getDescription(),
//                product.getCategory().getId(), product.getQuantity());
        return false;
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
