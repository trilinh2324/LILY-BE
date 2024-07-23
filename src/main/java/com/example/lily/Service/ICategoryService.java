package com.example.lily.Service;

import com.example.lily.Model.Category;

import java.util.List;

public interface ICategoryService extends IGenerateService<Category>{
    public List<Category> getAllCategories();

    Category getCategoryById(long categoryId);
}