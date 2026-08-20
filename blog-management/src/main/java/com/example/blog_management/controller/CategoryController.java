package com.example.blog_management.controller;

import com.example.blog_management.dto.request.CategoryRequest;
import com.example.blog_management.dto.response.ApiResponse;
import com.example.blog_management.dto.response.CategoryResponse;
import com.example.blog_management.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request)
    {
        CategoryResponse response = categoryService.createCategory(request);

        ApiResponse<CategoryResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "Category created successfully",
                        response
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories()
    {
        List<CategoryResponse> response = categoryService.getAllCategories();

        ApiResponse<List<CategoryResponse>> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Categories fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(@PathVariable UUID id)
    {
        CategoryResponse response = categoryService.getCategory(id);

        ApiResponse<CategoryResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Category fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryRequest request)
    {

        CategoryResponse response = categoryService.updateCategory(id, request);

        ApiResponse<CategoryResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Category updated successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable UUID id)
    {
        categoryService.deleteCategory(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.NO_CONTENT.value(),
                        "Category deleted successfully",
                        null
                );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}