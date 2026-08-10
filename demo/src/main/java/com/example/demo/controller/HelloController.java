package com.example.demo.controller;

import com.example.demo.dto.Hello;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController
{
    @PostMapping("/hello")
    public String postHello(@RequestBody Hello hello)
    {
        return "Hi! I am " + hello.name() + ". My age is " + hello.age();
    }
}