package com.ecommerce.services;

import com.ecommerce.entity.User;

import java.sql.ResultSet;
import java.util.List;

public interface UserService {
    boolean registerUser(User user);
    boolean loginUser(String email, String password);
    User getUserByID(int userId);
    List<User> getAllUsers();
    boolean updateUser(User user);
    void deleteUser(int id);
    User extractUserFromResultSet(ResultSet rs);
}
