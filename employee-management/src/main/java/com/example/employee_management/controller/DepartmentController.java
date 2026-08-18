package com.example.employee_management.controller;

import com.example.employee_management.dto.response.DepartmentEmployeeResponse;
import com.example.employee_management.entity.Department;
import com.example.employee_management.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController
{
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService)
    {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department)
    {
        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments()
    {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{departmentId}")
    public DepartmentEmployeeResponse getDepartmentWithEmployees(@PathVariable int departmentId)
    {
        return departmentService.getDepartmentWithEmployees(departmentId);
    }
}