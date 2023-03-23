package com.ecommerce.services;

import com.ecommerce.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(int categoryID);
    List<Category> getAllCategories();
}
