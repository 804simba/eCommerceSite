package com.ecommerce.dao;

import com.ecommerce.entity.User;
import com.ecommerce.config.DBConnection;
import com.ecommerce.enums.Role;

import java.sql.*;

public class UserDAO {
    Connection connection;

    public UserDAO() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
    }

    public boolean registerUser(User user) {
        final String REGISTER_USER = "INSERT INTO users (firstName, lastName, userName, address, password, email, phone, role, createdAt, modifiedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(REGISTER_USER)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUserName());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPhone());
            stmt.setString(8, user.getRole().toString());
            stmt.setTimestamp(9, user.getCreatedAt());
            stmt.setTimestamp(10, user.getModifiedAt());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean loginUser(String email, String password) {
        final String LOGIN_USER = "SELECT * FROM users WHERE email = ? AND password = ?";
        boolean result = false;
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(LOGIN_USER);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                if (rs.getString("email").equals(email) && rs.getString("password").equals(password)) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Incorrect login details: " + e.getMessage());
            result = false;
        }
        return result;
    }
    public User getUserById(int id) {
        User user = null;
        final String GET_USERS_BY_ID = "SELECT * FROM users WHERE userID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(GET_USERS_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userID = rs.getInt("id");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String userName = rs.getString("userName");
                    String address = rs.getString("address");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String role = rs.getString("role");
                    Timestamp createdAt = rs.getTimestamp("createdAt");
                    Timestamp modifiedAt = rs.getTimestamp("modifiedAt");
                    user = new User(userID,firstName, lastName, userName, address, password, email, phone, Role.valueOf(role), createdAt, modifiedAt);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
        }

        return user;
    }

}
