package com.example.blog_management.controller;

import com.example.blog_management.dto.request.PostRequest;
import com.example.blog_management.dto.response.ApiResponse;
import com.example.blog_management.dto.response.PostResponse;
import com.example.blog_management.service.PostService;
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

        ApiResponse<PostResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "Post created successfully",
                        response
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("X-Message", "Post created")
                .body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> getAllPosts()
    {
        List<PostResponse> response = postService.getAllPosts();

        ApiResponse<List<PostResponse>> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Posts fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPost(@PathVariable UUID id)
    {
        PostResponse response = postService.getPost(id);

        ApiResponse<PostResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Post fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(@PathVariable UUID id, @Valid @RequestBody PostRequest request)
    {
        PostResponse response = postService.updatePost(id, request);

        ApiResponse<PostResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Post updated successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable UUID id)
    {
        postService.deletePost(id);

        ApiResponse<Void> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.NO_CONTENT.value(),
                        "Post deleted successfully",
                        null
                );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}