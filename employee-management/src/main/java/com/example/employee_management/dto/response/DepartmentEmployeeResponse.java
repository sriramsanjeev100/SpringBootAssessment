package com.example.employee_management.dto.response;

import java.util.List;

public record DepartmentEmployeeResponse(int departmentId, String departmentName, List<String> employeeName)
{

}