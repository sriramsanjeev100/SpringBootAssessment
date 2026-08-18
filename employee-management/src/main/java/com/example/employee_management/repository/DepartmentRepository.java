package com.example.employee_management.repository;

import com.example.employee_management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer>
{
    @Query("""
        SELECT d
        FROM Department d
        LEFT JOIN FETCH d.employees
        WHERE d.id = :departmentId
        """)
    Optional<Department> findDepartmentWithEmployees(@Param("departmentId") int departmentId);
}