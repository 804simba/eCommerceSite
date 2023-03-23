package com.ecommerce.services.implementations;

import com.ecommerce.dao.UserDAO;
import com.ecommerce.entity.User;
import com.ecommerce.exceptions.UserNotFoundException;
import com.ecommerce.services.UserService;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Override
    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }

    @Override
    public boolean confirmUserLoginCredentials(String email, String password) {
        return userDAO.confirmUserLoginCredentials(email, password);
    }

    @Override
    public User getUserByID(int userId) throws UserNotFoundException {
        return userDAO.getUserByID(userId);
    }
}
