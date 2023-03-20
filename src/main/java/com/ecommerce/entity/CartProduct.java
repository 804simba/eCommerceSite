package com.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor
public class CartProduct {
    private int cartID;
    private int UserID;
    private Product product;
    private int quantity;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
