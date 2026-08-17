package org.example.studentcourse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.example.studentcourse.dto.request.StudentRequestDto;
import org.example.studentcourse.dto.response.StudentCourseResponse;
import org.example.studentcourse.dto.response.StudentResponse;
import org.example.studentcourse.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController
{
    private final StudentService studentService;
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(
            summary = "Create a New Student",
            description = "Creates a Student and Adds them to a Course"
    )
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody StudentRequestDto dto)
    {
        StudentResponse response = studentService.addStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents()
    {
        List<StudentResponse> response = studentService.getAllStudents();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get Student by ID",
            description = "Returns Student Details for the Given Student ID"
    )
    public ResponseEntity<StudentResponse> getStudentById(@Parameter(description = "Student ID")@PathVariable int id)
    {
        StudentResponse response = studentService.getStudentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    @Operation(
            summary = "Get Students by Course",
            description = "Returns all Students who joined in the Given Course"
    )
    public ResponseEntity<StudentCourseResponse> getStudentsByCourse(@Parameter(description = "Course ID")@PathVariable int courseId)
    {
        StudentCourseResponse response = studentService.getStudentsByCourse(courseId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Student",
            description = "Deletes a Student using the Student ID"
    )
    public ResponseEntity<String> deleteStudent(@Parameter(description = "Student ID")@PathVariable int id)
    {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}