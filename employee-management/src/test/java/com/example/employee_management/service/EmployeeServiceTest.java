package com.example.employee_management.service;

import com.example.employee_management.entity.Department;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.repository.DepartmentRepository;
import com.example.employee_management.repository.EmployeeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest
{
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees()
    {
        Employee employee1 = new Employee(1, "Sriram", "sriram@gmail.com", 50000, null);
        Employee employee2 = new Employee(2, "John", "john@gmail.com", 60000, null);
        List<Employee> employees = List.of(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> result = employeeService.getAllEmployees();
        assertNotNull(result);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("Sriram", result.get(0).getName()),
                () -> assertEquals("John", result.get(1).getName())
        );
        verify(employeeRepository).findAll();
    }

    @Test
    void testGetEmployee()
    {
        Employee employee = new Employee(1, "Sriram", "sriram@gmail.com", 50000, null);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        Employee result = employeeService.getEmployee(1);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("Sriram", result.getName()),
                () -> assertEquals("sriram@gmail.com", result.getEmail()),
                () -> assertEquals(50000, result.getSalary())
        );
        verify(employeeRepository).findById(1);
    }

    @Test
    void testCreateEmployee()
    {
        Department department = new Department(1, "IT", null);
        Employee employee = new Employee(null, "Sriram", "sriram@gmail.com", 50000, department);
        Employee savedEmployee = new Employee(1, "Sriram", "sriram@gmail.com", 50000, department);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);
        Employee result = employeeService.createEmployee(employee);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("Sriram", result.getName()),
                () -> assertEquals("sriram@gmail.com", result.getEmail()),
                () -> assertEquals(50000, result.getSalary()),
                () -> assertEquals(department, result.getDepartment())
        );
        verify(departmentRepository).findById(1);
        verify(employeeRepository).save(employee);
    }
}