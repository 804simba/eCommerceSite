package com.ecommerce.entity;

import com.ecommerce.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor @AllArgsConstructor
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
}
