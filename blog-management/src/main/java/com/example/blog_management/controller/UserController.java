package com.example.blog_management.controller;

import com.example.blog_management.entity.User;
import com.example.blog_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User response = userService.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("X-Message", "User created")
                .body(response);
    }
}