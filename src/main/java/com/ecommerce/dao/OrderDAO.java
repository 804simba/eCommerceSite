package com.ecommerce.dao;

import com.ecommerce.entity.Order;
import com.ecommerce.config.DBConnection;

import java.sql.*;

public class OrderDAO {
    private Connection connection;
    private UserDAO userDAO = new UserDAO();

    public OrderDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            System.out.println("Database exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Exception: " + e.getMessage());
        }
    }

    public boolean createOrder(Order order) {
        boolean success = false;
        String sql = "INSERT INTO orders (orderID, userId, orderDate, totalAmount, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getOrderID());
            stmt.setInt(2, order.getUserID());
            stmt.setTimestamp(3, order.getOrderDate());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getStatus());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                success = true;
            }
        } catch (SQLException e) {
            System.out.println("Error creating order: " + e.getMessage());
        }

        return success;
    }

//    public Order getOrderById(int id) {
//        Order order = null;
//        final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE orderID = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(GET_ORDER_BY_ID)) {
//            stmt.setInt(1, id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    int orderId = rs.getInt("orderID");
//                    int userID = rs.getInt("userID");
//                    Timestamp orderDate = rs.getTimestamp("orderDate");
//                    double totalAmount = rs.getDouble("totalAmount");
//                    String status = rs.getString("status");
//                    List<OrderDetail> orderDetails = getOrderDetailsByOrderId(orderId);
//                    order = new Order(orderId, userID, orderDate, totalAmount, status, orderDetails);
//                }
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error retrieving order: " + e.getMessage());
//        }
//
//        return order;
//    }

//    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
//        List<OrderDetail> orderDetails = new ArrayList<>();
//        String sql = "SELECT * FROM orderDetails WHERE orderID = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, orderId);
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    int orderDetailID = rs.getInt("orderDetailID");
//                    int orderID = rs.getInt("orderID");
//                    int productId = rs.getInt("productID");
//                    int quantity = rs.getInt("quantity");
//                    double price = rs.getDouble("price");
//                    OrderDetail orderDetail = new OrderDetail(orderDetailID, orderID, productId, quantity, price);
//                    orderDetails.add(orderDetail);
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Error retrieving order details: " + e.getMessage());
//        }
//        return orderDetails;
//    }

}
