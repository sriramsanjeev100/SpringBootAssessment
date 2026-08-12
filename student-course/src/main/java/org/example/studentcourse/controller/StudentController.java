package org.example.studentcourse.controller;

import org.example.studentcourse.dto.StudentRequestDto;
import org.example.studentcourse.entity.Student;
import org.example.studentcourse.service.StudentService;
import org.springframework.web.bind.annotation.*;

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
    public Student addStudent(@RequestBody StudentRequestDto dto)
    {
        return studentService.addStudent(dto);
    }

    @GetMapping
    public List<Student> getAllStudents()
    {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id)
    {
        return studentService.getStudentById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<Student> getStudentsByCourse(@PathVariable int courseId)
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