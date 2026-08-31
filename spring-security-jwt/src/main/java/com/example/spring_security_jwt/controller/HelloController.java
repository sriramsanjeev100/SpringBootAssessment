package com.example.spring_security_jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("/hello")
    public String hello()
    {
        return "Hello";
    }
}

//LEO - eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZW93aW5zdG9uIiwiaWF0IjoxNzg4MTQ5OTUzLCJleHAiOjE3ODgxNTE3NTN9.nAnHEVA0yyYKEq2U4el1kOW3gYiedF_5jFv6Vk1UVWk
//AKJ - eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYXJvbmtqaWp1IiwiaWF0IjoxNzg4MTU0NDY4LCJleHAiOjE3ODgxNTYyNjh9.Nv8VetavNtKI7zIjco8rGakcjCgWi6uqVO6rUXBval8