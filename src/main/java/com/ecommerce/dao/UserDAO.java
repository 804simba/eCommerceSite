package com.ecommerce.dao;

import com.ecommerce.entity.User;
import com.ecommerce.config.DBConnection;
import com.ecommerce.utils.PasswordValidation;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
///Users/decagon/IdeaProjects/ECommerce/src/main/webapp/assets/images/product/MOT-0069 (1).jpg
public class UserDAO {
    Connection connection;
    PreparedStatement stmt;

    public UserDAO() {
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        }
    }
    private User resultSetToUser(ResultSet rs) throws SQLException {
        int userID = rs.getInt("id");
        String firstName = rs.getString("firstName");
        String lastName = rs.getString("lastName");
        String email = rs.getString("email");
        String password = rs.getString("password");
        Timestamp createdAt = rs.getTimestamp("createdAt");
        Timestamp modifiedAt = rs.getTimestamp("modifiedAt");
        return new User(userID,firstName, lastName, email, password, createdAt, modifiedAt);
    }

    public boolean registerUser(User user) {
        final String REGISTER_USER = "INSERT INTO users (firstName, lastName, email, password) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(REGISTER_USER)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean confirmUserLoginCredentials(String email, String password) {
        final String LOGIN_USER = "SELECT * FROM users WHERE email = ? AND password = ?";
        boolean result = false;
        String hashedPassword = getPasswordFromDB(email);
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            PreparedStatement st = conn.prepareStatement(LOGIN_USER);
            st.setString(1, email);
            st.setString(2, PasswordValidation.hashPassword(password));
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                if (rs.getString("email").equals(email)
                        && rs.getString("password").equals(hashedPassword)) {
                    result = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Incorrect login details: " + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password "+e.getMessage());
        }
        return result;
    }
    private String getPasswordFromDB(String email) {
        final String USER_PASSWORD = "SELECT password FROM Users WHERE email = ?";
        String password = null;
        try {
            PreparedStatement st = connection.prepareStatement(USER_PASSWORD);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving password: " + e.getMessage());
        }
        return password;
    }
    public User getUserByID(int id) {
        User user = null;
        final String GET_USERS_BY_ID = "SELECT * FROM users WHERE userID = ?";
        try {
            stmt = connection.prepareStatement(GET_USERS_BY_ID);
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = resultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
        }

        return user;
    }
}
