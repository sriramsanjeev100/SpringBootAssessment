package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController
{
    @GetMapping("/employees/search")
    public String searchEmployees(@RequestParam String department, @RequestParam(required = false) String name)
    {
        return "Department: " + department + ", Name: " + name;
    }

    @GetMapping("/employees")
    public String getEmployees(@RequestParam int page, @RequestParam int size)
    {
        return "Page: " + page + ", Size: " + size;
    }

    @GetMapping("/employees/{id}")
    public String getEmployee(@PathVariable int id)
    {
        return "Employee ID: " + id;
    }
}