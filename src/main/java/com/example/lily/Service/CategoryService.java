package com.example.lily.Service;

import com.example.lily.Model.Category;
import com.example.lily.Repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository iCategoryRepository;

    public List<Category> getAllCategories() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(long categoryId) {
        return iCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }


    @Override
    public Page<Category> findAll(Pageable pageable) {
        return iCategoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return iCategoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return iCategoryRepository.save(category);
    }

    @Override
    public void remove(long id) {
        iCategoryRepository.deleteById(id);
    }
}
