package com.example.blog_management.controller;

import  com.example.blog_management.dto.request.PostRequest;
import com.example.blog_management.dto.response.ApiResponse;
import com.example.blog_management.dto.response.PostResponse;
import com.example.blog_management.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController
{
    private final PostService postService;
    public PostController(PostService postService)
    {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(@Valid @RequestBody PostRequest request)
    {
        PostResponse response = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Post created successfully", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts()
    {
        List<PostResponse> response = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Posts fetched successfully", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable UUID id)
    {
        PostResponse response = postService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Post fetched successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(@PathVariable UUID id, @Valid @RequestBody PostRequest request)
    {
        PostResponse response = postService.updatePost(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Post updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable UUID id)
    {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Post deleted successfully", null));
    }

    @GetMapping("/category/{categoryId}/recent")
    public ResponseEntity<ApiResponse<Page<PostResponse>>> findRecentPostsByCategory(@PathVariable UUID categoryId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    {
        Page<PostResponse> response = postService.findRecentPostsByCategory(categoryId, page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success("Recent posts fetched successfully", response));
    }
}