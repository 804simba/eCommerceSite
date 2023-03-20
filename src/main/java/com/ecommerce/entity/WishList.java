package com.ecommerce.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WishList {
    private int userID;
    private int productID;
    private LocalDateTime createdAt;
}
