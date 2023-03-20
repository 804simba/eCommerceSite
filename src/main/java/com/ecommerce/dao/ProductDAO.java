package com.ecommerce.dao;

import com.ecommerce.config.DBConnection;
import com.ecommerce.entity.Product;
import com.ecommerce.enums.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(2, 1, 3));

    }
    public ProductDAO() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
    }
    private Product resultSetToProduct(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");
        Category category = Category.valueOf(resultSet.getString("category"));
        int quantity = resultSet.getInt("quantity");
        boolean isDeleted = resultSet.getBoolean("is_deleted");
        byte[] image = resultSet.getBytes("image");
        String base64Image = Base64.getEncoder().encodeToString(image);
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        Timestamp modifiedAt = resultSet.getTimestamp("modified_at");
        return new Product(productId, name, price, description, category, quantity, isDeleted, image, base64Image, createdAt, modifiedAt);
    }
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM products";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = resultSetToProduct(resultSet);
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return products;
    }

    public Product getProductById(int productId) {
        Product product = null;
        try {
            String sql = "SELECT * FROM products WHERE productID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = resultSetToProduct(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return product;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean added = false;
        try {
            String sql = "INSERT INTO products (name, description, image_url, price) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getImageUrl());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.executeUpdate();
            added = true;
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return added;
    }

    public boolean updateProduct(Product product) {
        boolean updated = false;
        try {
            String sql = "UPDATE products SET name=?, description=?, image_url=?, price=? WHERE product_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getImageUrl());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getProductId());
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return updated;
    }

    public boolean deleteProduct(int productId) {
        boolean deleted = false;
        try {
            String sql = "DELETE FROM products WHERE productID=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return deleted;
    }
}
