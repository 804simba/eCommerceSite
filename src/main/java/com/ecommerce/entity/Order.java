package com.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int orderID;
    private int userID;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;
    private List<OrderDetail> orderDetails;
}
