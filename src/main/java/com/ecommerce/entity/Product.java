package com.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class Product {
    private int productID;
    private String productName;
    private String productPrice;
    private String imageName;
    private String description;
    private Category category;
    private int quantity;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public Product(int productID, String productName, String productPrice, String description, Category category, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
    }
}
