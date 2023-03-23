package com.ecommerce.dao;

import com.ecommerce.entity.Category;

import java.util.List;

public interface ICategoryDAO {
    Category getCategory(int categoryID);
    List<Category> getAllCategories();
}
