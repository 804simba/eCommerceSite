package com.ecommerce.dao;

import com.ecommerce.entity.User;
import com.ecommerce.exceptions.UserNotFoundException;

public interface IUserDAO {
    boolean registerUser(User user);
    boolean confirmUserLoginCredentials(String email, String password);
    User getUserByID(int id) throws UserNotFoundException;
}
