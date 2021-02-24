package com.example.hotelmanager.dao;

import com.example.hotelmanager.model.Category;

import java.util.List;

public interface CategoryDao {

    void addCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(int id);
    Category getCategoryById(int id);
    List<Category> getCategories();

}
