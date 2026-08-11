package org.example.studentcrud.service;

import org.example.studentcrud.dto.StudentDto;
import org.example.studentcrud.entity.Student;
import org.example.studentcrud.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    // CREATE
    public Student addStudent(StudentDto student)
    {
        Student newStudent = new Student();
        newStudent.setName(student.name());
        newStudent.setEmail(student.email());
        newStudent.setCourse(student.course());
        return studentRepository.save(newStudent);
    }

    // READ ALL
    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    // READ BY ID
    public Student getStudentById(int id)
    {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // UPDATE
    public Student updateStudent(int id, StudentDto student)
    {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existingStudent.setName(student.name());
        existingStudent.setEmail(student.email());
        existingStudent.setCourse(student.course());

        return studentRepository.save(existingStudent);
    }

    // DELETE
    public void deleteStudent(int id)
    {
        studentRepository.deleteById(id);
    }
}