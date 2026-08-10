package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController
{
    @GetMapping("/hello")
    public String getHello()
    {
        return "Hello World - GET";
    }

    @PostMapping("/hello")
    public String postHello()
    {
        return "Hello World - POST";
    }

    @PutMapping("/hello")
    public String putHello()
    {
        return "Hello World - PUT";
    }

    @DeleteMapping("/hello")
    public String deleteHello()
    {
        return "Hello World - DELETE";
    }
}