package com.example.hotelmanager.service;

import com.example.hotelmanager.model.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int id);
    Category getCategoryById(int id);
    List<Category> getCategories();
}
