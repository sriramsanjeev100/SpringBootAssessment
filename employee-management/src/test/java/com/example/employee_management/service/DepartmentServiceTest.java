package com.example.employee_management.service;

import com.example.employee_management.dto.response.DepartmentEmployeeResponse;
import com.example.employee_management.entity.Department;
import com.example.employee_management.entity.Employee;
import com.example.employee_management.repository.DepartmentRepository;

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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest
{
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDepartment()
    {
        Department department = new Department(1, "IT", null);
        when(departmentRepository.save(department)).thenReturn(department);
        Department result = departmentService.createDepartment(department);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("IT", result.getName())
        );

        verify(departmentRepository).save(department);
    }

    @Test
    void testGetAllDepartments()
    {
        Department department1 = new Department(1, "IT", null);
        Department department2 = new Department(2, "HR", null);
        List<Department> departments = List.of(department1, department2);
        when(departmentRepository.findAll()).thenReturn(departments);
        List<Department> result = departmentService.getAllDepartments();
        assertNotNull(result);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("IT", result.get(0).getName()),
                () -> assertEquals("HR", result.get(1).getName())
        );

        verify(departmentRepository).findAll();
    }

    @Test
    void testGetDepartment()
    {
        Department department = new Department(1, "IT", null);
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        Department result = departmentService.getDepartment(1);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("IT", result.getName())
        );

        verify(departmentRepository).findById(1);
    }

    @Test
    void testGetDepartmentWithEmployees()
    {
        Employee employee1 = new Employee(1, "Sriram", "sriram@gmail.com", 50000, null);
        Employee employee2 = new Employee(2, "John", "john@gmail.com", 60000, null);
        List<Employee> employees = List.of(employee1, employee2);
        Department department = new Department(1, "IT", employees);
        when(departmentRepository.findDepartmentWithEmployees(1)).thenReturn(Optional.of(department));
        DepartmentEmployeeResponse result = departmentService.getDepartmentWithEmployees(1);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.departmentId()),
                () -> assertEquals("IT", result.departmentName()),
                () -> assertEquals(2, result.employeeName().size()),
                () -> assertEquals("Sriram", result.employeeName().get(0)),
                () -> assertEquals("John", result.employeeName().get(1))
        );

        verify(departmentRepository).findDepartmentWithEmployees(1);
    }

}