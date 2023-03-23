package com.ecommerce.dao;

import com.ecommerce.config.DBConnection;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    CategoryDAO categoryDAO = new CategoryDAO();

    public ProductDAO() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
    }

    private Product resultSetToProduct(ResultSet resultSet) throws SQLException, ProductNotFoundException {
        Product product = new Product();
        product.setProductID(resultSet.getInt("productID"));
        product.setProductName(resultSet.getString("productName"));
        product.setProductPrice(resultSet.getString("productPrice"));
        product.setImageName(resultSet.getString("imageName"));
        product.setDescription(resultSet.getString("description"));
        product.setCategory(categoryDAO.getCategory(resultSet.getInt("categoryID")));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setCreatedAt(resultSet.getTimestamp("createdAt"));
        product.setModifiedAt(resultSet.getTimestamp("modifiedAt"));
        return product;
    }
    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = resultSetToProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException  e) {
            System.out.println("Product/SQL exception: " + e.getMessage());
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException("Product not found...");
        }
        return products;
    }
    @Override
    public Product getProductById(int productID) {
        Product product = null;
        try {
            String sql = "SELECT * FROM products WHERE productID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = resultSetToProduct(resultSet);
            }
        } catch (SQLException | ProductNotFoundException e) {
            System.out.println("SQL exception: " + e.getMessage());
        }
        return product;
    }
    @Override
    public boolean addProduct(String productName, String productPrice, String imageName, String description, String categoryID, String quantity) {
        boolean added = false;
        final String ADD_PRODUCT = "INSERT INTO Products (productName, productPrice, imageName, description, categoryID, quantity) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, productName);
            preparedStatement.setBigDecimal(2, new BigDecimal(productPrice));
            preparedStatement.setString(3, imageName);
            preparedStatement.setString(4, description);
            preparedStatement.setString(5, categoryID);
            preparedStatement.setString(6, quantity);
            preparedStatement.executeUpdate();
            added = true;
        } catch (SQLException e) {
            System.out.println("SQL exception: " + e.getMessage());
        }
        return added;
    }
    @Override
    public boolean editProduct(int productID, String productName, String imageName, String productPrice, String productDescription, String quantity) {
        boolean updated = false;
        final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, imageName = ?, productPrice = ?, description = ?, quantity = ? WHERE productID = ?";
        try {
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, productName);
            preparedStatement.setString(2, imageName);
            preparedStatement.setString(3, productPrice);
            preparedStatement.setString(4, productDescription);
            preparedStatement.setString(5, quantity);
            preparedStatement.setInt(6, productID);
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            System.out.println("SQL exception: " + e.getMessage());
        }
        return updated;
    }
    @Override
    public boolean deleteProduct(int productId) {
        boolean deleted = false;
        try {
            String sql = "DELETE FROM products WHERE productID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (SQLException e) {
            System.out.println("SQL exception: " + e.getMessage());
        }
        return deleted;
    }
    @Override
    public List<Product> getListOfProductsHandler(String query) {
        List<Product> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt(1);
                String productName = resultSet.getString(2);
                String productPrice = resultSet.getString(3);
                String description = resultSet.getString(4);
                Category category = categoryDAO.getCategory(resultSet.getInt(5));
                int quantity = resultSet.getInt(6);
                Timestamp createdAt = resultSet.getTimestamp(8);
                Timestamp modifiedAt = resultSet.getTimestamp(9);
                Product product = new Product(productID, productName, productPrice, description, category, quantity);
                product.setCreatedAt(createdAt);
                product.setModifiedAt(modifiedAt);
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    @Override
    public List<Product> get12ProductsOfPage(int index) {
        String query = "SELECT * FROM product WHERE isDeleted = false LIMIT " + ((index - 1) * 12) + ", 12";
        return getListOfProductsHandler(query);
    }
    @Override
    public List<String> getAllProductImages() {
        List<String> images = new ArrayList<>();
        String sql = "SELECT imageName FROM products";
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String imageName = resultSet.getString("imageName");
                images.add(imageName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

}
