package com.example.hotelmanager.service;

import com.example.hotelmanager.dao.CategoryDao;
import com.example.hotelmanager.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService{

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    @Override
    public void addCategory(Category category) {
        this.categoryDao.addCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        this.categoryDao.updateCategory(category);
    }

    @Override
    public void deleteCategory(int id) {
        this.categoryDao.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(int id) {
        return this.categoryDao.getCategoryById(id);
    }

    @Override
    public List<Category> getCategories() {
        return this.categoryDao.getCategories();
    }

}
