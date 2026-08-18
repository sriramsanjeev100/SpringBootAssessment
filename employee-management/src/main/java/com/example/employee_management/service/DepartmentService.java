package com.example.employee_management.service;

import com.example.employee_management.entity.Department;
import com.example.employee_management.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService
{
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository)
    {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department)
    {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments()
    {
        return departmentRepository.findAll();
    }

    public Department getDepartment(int id)
    {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Department getDepartmentWithEmployees(int departmentId)
    {
        return departmentRepository.findDepartmentWithEmployees(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }
}