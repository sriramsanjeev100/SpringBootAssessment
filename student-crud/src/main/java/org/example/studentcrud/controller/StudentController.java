package org.example.studentcrud.controller;

import org.example.studentcrud.dto.StudentDto;
import org.example.studentcrud.entity.Student;
import org.example.studentcrud.service.StudentService;
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
    public Student addStudent(@RequestBody StudentDto student)
    {
        return studentService.addStudent(student);
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

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id,@RequestBody StudentDto student)
    {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id)
    {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }
}
