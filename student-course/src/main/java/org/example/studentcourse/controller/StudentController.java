package org.example.studentcourse.controller;

import org.example.studentcourse.dto.request.StudentRequestDto;
import org.example.studentcourse.dto.response.StudentCourseResponse;
import org.example.studentcourse.dto.response.StudentResponse;
import org.example.studentcourse.service.StudentService;
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
    public StudentResponse addStudent(@RequestBody StudentRequestDto dto)
    {
        return studentService.addStudent(dto);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable int id)
    {
        return studentService.getStudentById(id);
    }

    @GetMapping("/course/{courseId}")
    public StudentCourseResponse getStudentsByCourse(@PathVariable int courseId)
    {
        return studentService.getStudentsByCourse(courseId);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id)
    {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }
}