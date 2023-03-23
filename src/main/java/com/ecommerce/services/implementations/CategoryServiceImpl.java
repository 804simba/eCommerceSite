package com.ecommerce.services.implementations;

import com.ecommerce.dao.ICategoryDAO;
import com.ecommerce.entity.Category;
import com.ecommerce.services.CategoryService;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    ICategoryDAO categoryDAO;
    @Override
    public Category getCategory(int categoryID) {
        return categoryDAO.getCategory(categoryID);
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
}
