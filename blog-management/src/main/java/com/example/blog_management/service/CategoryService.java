package com.example.blog_management.service;

import com.example.blog_management.dto.request.CategoryRequest;
import com.example.blog_management.dto.response.CategoryResponse;
import com.example.blog_management.entity.Category;
import com.example.blog_management.exception.CategoryNotFoundException;
import com.example.blog_management.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CategoryService
{
    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse createCategory(CategoryRequest request)
    {
        log.info("Creating category with name: {}", request.name());
        Category category = new Category();
        setCategoryFields(category, request);
        Category savedCategory = categoryRepository.save(category);
        return mapToResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories()
    {
        log.info("Fetching all categories");
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CategoryResponse getCategory(UUID id)
    {
        log.info("Fetching category with id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        return mapToResponse(category);
    }

    public CategoryResponse updateCategory(UUID id, CategoryRequest request)
    {
        log.info("Updating category with id: {}", id);
        Category category = categoryRepository.findById(id)
                        .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        setCategoryFields(category, request);
        Category updatedCategory = categoryRepository.save(category);
        log.info("Category updated successfully with id: {}", id);
        return mapToResponse(updatedCategory);
    }

    public void deleteCategory(UUID id)
    {
        log.info("Deleting category with id: {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        categoryRepository.delete(category);
        log.info("Category deleted successfully with id: {}", id);
    }

    private void setCategoryFields(Category category,CategoryRequest request)
    {
        category.setName(request.name());
    }

    private CategoryResponse mapToResponse(Category category)
    {
        return new CategoryResponse(category.getId(), category.getName());
    }
}