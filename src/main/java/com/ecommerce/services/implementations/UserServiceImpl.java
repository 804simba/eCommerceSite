package com.ecommerce.services.implementations;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.entity.User;
import com.ecommerce.services.UserService;
import lombok.AllArgsConstructor;

import java.sql.ResultSet;
import java.util.List;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Override
    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }

    @Override
    public boolean loginUser(String email, String password) {
        return userDAO.loginUser(email, password);
    }

    @Override
    public User getUserByID(int userId) {
        return userDAO.getUserById(userId);
    }


    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public User extractUserFromResultSet(ResultSet rs) {
        return null;
    }
}
