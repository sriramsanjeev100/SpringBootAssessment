package org.example.studentcourse.controller;

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
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable int id)
    {
        StudentResponse response = studentService.getStudentById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<StudentCourseResponse> getStudentsByCourse(@PathVariable int courseId)
    {
        StudentCourseResponse response = studentService.getStudentsByCourse(courseId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id)
    {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}