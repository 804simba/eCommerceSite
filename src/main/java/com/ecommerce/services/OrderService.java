package com.ecommerce.services;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;

public interface OrderService {
    boolean createOrder(Order order, User user);
    Order getOrderById(int id);
}
