package com.ecommerce.dao;

import com.ecommerce.config.DBConnection;
import com.ecommerce.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public CategoryDAO() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
    }
    @Override
    public Category getCategory(int categoryID) {
        Category category = new Category();
        final String SELECT_BY_CATEGORY = "SELECT * FROM Categories WHERE categoryID = " + categoryID;
        try {
            preparedStatement = connection.prepareStatement(SELECT_BY_CATEGORY);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }
    @Override
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM Categories";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                list.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
