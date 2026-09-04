package com.example.job_portal.controller;

import com.example.job_portal.dto.request.LoginRequest;
import com.example.job_portal.dto.request.RegisterRequest;
import com.example.job_portal.dto.response.LoginResponse;
import com.example.job_portal.dto.response.UserResponse;
import com.example.job_portal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{
    private final UserService userService;
    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request)
    {
        UserResponse response = userService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request)
    {
        LoginResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}