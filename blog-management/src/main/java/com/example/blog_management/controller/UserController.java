package com.example.blog_management.controller;

import com.example.blog_management.dto.request.UserRequest;
import com.example.blog_management.dto.response.ApiResponse;
import com.example.blog_management.dto.response.UserResponse;
import com.example.blog_management.service.UserService;
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
@RequestMapping("/api/users")
public class UserController
{
    private final UserService userService;
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@Valid @RequestBody UserRequest request)
    {
        UserResponse response = userService.createUser(request);
        ApiResponse<UserResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "User created successfully",
                        response
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("X-Message", "User created")
                .body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers()
    {
        List<UserResponse> response = userService.getAllUsers();
        ApiResponse<List<UserResponse>> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Users fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable UUID id)
    {
        UserResponse response = userService.getUser(id);

        ApiResponse<UserResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "User fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequest request)
    {
        UserResponse response = userService.updateUser(id, request);
        ApiResponse<UserResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "User updated successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable UUID id)
    {
        userService.deleteUser(id);
        ApiResponse<Void> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.NO_CONTENT.value(),
                        "User deleted successfully",
                        null
                );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}