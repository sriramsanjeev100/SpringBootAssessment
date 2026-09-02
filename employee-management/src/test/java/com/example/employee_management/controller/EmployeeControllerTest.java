package com.example.employee_management.controller;

import com.example.employee_management.entity.Department;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest
{
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

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
        when(employeeService.getAllEmployees()).thenReturn(employees);
        List<Employee> result = employeeController.getAllEmployees();
        assertNotNull(result);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("Sriram", result.get(0).getName()),
                () -> assertEquals("John", result.get(1).getName())
        );

        verify(employeeService).getAllEmployees();
    }

    @Test
    void testGetEmployee()
    {
        Employee employee = new Employee(1, "Sriram", "sriram@gmail.com", 50000, null);
        when(employeeService.getEmployee(1)).thenReturn(employee);
        Employee result = employeeController.getEmployee(1);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("Sriram", result.getName()),
                () -> assertEquals("sriram@gmail.com", result.getEmail()),
                () -> assertEquals(50000, result.getSalary())
        );

        verify(employeeService).getEmployee(1);
    }

    @Test
    void testCreateEmployee()
    {
        Department department = new Department(1, "IT", null);
        Employee employee = new Employee(null, "Sriram", "sriram@gmail.com", 50000, department);
        Employee savedEmployee = new Employee(1, "Sriram", "sriram@gmail.com", 50000, department);
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(savedEmployee);
        Employee result = employeeController.createEmployee(employee);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("Sriram", result.getName()),
                () -> assertEquals("sriram@gmail.com", result.getEmail()),
                () -> assertEquals(50000, result.getSalary()),
                () -> assertEquals("IT", result.getDepartment().getName())
        );

        verify(employeeService).createEmployee(any(Employee.class));
    }
}