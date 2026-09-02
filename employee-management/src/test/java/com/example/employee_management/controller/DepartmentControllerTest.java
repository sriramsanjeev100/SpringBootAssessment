package com.example.employee_management.controller;

import com.example.employee_management.dto.response.DepartmentEmployeeResponse;
import com.example.employee_management.entity.Department;
import com.example.employee_management.service.DepartmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DepartmentControllerTest
{
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDepartment()
    {
        Department department = new Department(1, "IT", null);
        when(departmentService.createDepartment(department)).thenReturn(department);
        Department result = departmentController.createDepartment(department);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("IT", result.getName())
        );

        verify(departmentService).createDepartment(department);
    }

    @Test
    void testGetAllDepartments()
    {
        Department department1 = new Department(1, "IT", null);
        Department department2 = new Department(2, "HR", null);
        List<Department> departments = List.of(department1, department2);
        when(departmentService.getAllDepartments()).thenReturn(departments);
        List<Department> result = departmentController.getAllDepartments();
        assertNotNull(result);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("IT", result.get(0).getName()),
                () -> assertEquals("HR", result.get(1).getName())
        );

        verify(departmentService).getAllDepartments();
    }

    @Test
    void testGetDepartmentWithEmployees()
    {
        List<String> employeeNames = List.of("Sriram", "John");
        DepartmentEmployeeResponse response = new DepartmentEmployeeResponse(1, "IT", employeeNames);
        when(departmentService.getDepartmentWithEmployees(1)).thenReturn(response);
        DepartmentEmployeeResponse result = departmentController.getDepartmentWithEmployees(1);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.departmentId()),
                () -> assertEquals("IT", result.departmentName()),
                () -> assertEquals(2, result.employeeName().size()),
                () -> assertEquals("Sriram", result.employeeName().get(0)),
                () -> assertEquals("John", result.employeeName().get(1))
        );

        verify(departmentService).getDepartmentWithEmployees(1);
    }
}