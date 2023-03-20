package com.ecommerce.services.implementations;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.User;
import com.ecommerce.services.OrderService;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean createOrder(Order order, User user) {
        return false;
    }

    @Override
    public Order getOrderById(int id) {
        return null;
    }
}
