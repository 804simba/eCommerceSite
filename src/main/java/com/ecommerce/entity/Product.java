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
    private double productPrice;
    private String description;
    private Category category;
    private int quantity;
    private boolean isDeleted;
    private byte[] image;
    private String base64Image;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public Product(int productID, String productName, double productPrice, String description, Category category, int quantity, boolean isDeleted, String base64Image) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.isDeleted = isDeleted;
        this.base64Image = base64Image;
    }
}
