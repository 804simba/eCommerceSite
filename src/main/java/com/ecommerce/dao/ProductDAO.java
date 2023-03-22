package com.ecommerce.dao;

import com.ecommerce.config.DBConnection;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ProductDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    CategoryDAO categoryDAO = new CategoryDAO();
    public ProductDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Exception: " + e.getMessage());
        }
    }
    private Product resultSetToProduct(ResultSet resultSet) throws SQLException {
        int productId = resultSet.getInt("product_id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        String description = resultSet.getString("description");
        Category category = categoryDAO.getCategory(resultSet.getInt(6));
        int quantity = resultSet.getInt("quantity");
        boolean isDeleted = resultSet.getBoolean("is_deleted");
        byte[] image = resultSet.getBytes("image");
        String base64Image = Base64.getEncoder().encodeToString(image);
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        Timestamp modifiedAt = resultSet.getTimestamp("modified_at");
        return new Product(productId, name, price, description, category, quantity, isDeleted, base64Image);
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
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return product;
    }

    public boolean addProduct(String productName, double productPrice, String description, int categoryID, int quantity, InputStream productImage) {
        boolean added = false;
        final String ADD_PRODUCT = "INSERT INTO Products (productName, productPrice, description, categoryID, quantity, image) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, productName);
            preparedStatement.setDouble(2, productPrice);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, categoryID);
            preparedStatement.setInt(5, quantity);
            preparedStatement.setBinaryStream(6, productImage);
            preparedStatement.executeUpdate();
            added = true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return added;
    }

    public boolean editProduct(int productID, String productName, InputStream productImage, double productPrice, String productDescription, int categoryID, int quantity) {
        boolean updated = false;
        final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, image = ?, productPrice = ?, description = ?, categoryID = ?, quantity = ? WHERE productID = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, productName);
            preparedStatement.setBinaryStream(2, productImage);
            preparedStatement.setDouble(3, productPrice);
            preparedStatement.setString(4, productDescription);
            preparedStatement.setInt(5, categoryID);
            preparedStatement.setInt(6, quantity);
            preparedStatement.setInt(7, productID);
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return updated;
    }

    public boolean deleteProduct(int productId) {
        boolean deleted = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String sql = "DELETE FROM products WHERE productID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            preparedStatement.executeUpdate();
            deleted = true;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
        return deleted;
    }
    public List<Product> getListOfProductsHandler(String query) {
        List<Product> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productID = resultSet.getInt(1);
                String productName = resultSet.getString(2);
                double productPrice = resultSet.getDouble(3);
                String description = resultSet.getString(4);
                Category category = categoryDAO.getCategory(resultSet.getInt(5));
                int quantity = resultSet.getInt(6);
                boolean isDeleted = resultSet.getBoolean(7);

                byte[] imageData = resultSet.getBytes(8);
                String base64Image = Base64.getEncoder().encodeToString(imageData);
                Timestamp createdAt = resultSet.getTimestamp(8);
                Timestamp modifiedAt = resultSet.getTimestamp(9);
                Product product = new Product(productID, productName, productPrice, description, category, quantity, isDeleted, base64Image);
                product.setCreatedAt(createdAt);
                product.setModifiedAt(modifiedAt);
                list.add(product);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public List<Product> get12ProductsOfPage(int index) {
        String query = "SELECT * FROM product WHERE isDeleted = false LIMIT " + ((index - 1) * 12) + ", 12";
        return getListOfProductsHandler(query);
    }
}
