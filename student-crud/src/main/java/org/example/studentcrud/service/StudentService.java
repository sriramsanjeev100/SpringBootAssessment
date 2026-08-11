package org.example.studentcrud.service;

import org.example.studentcrud.dto.StudentDto;
import org.example.studentcrud.entity.Student;
import org.example.studentcrud.entity.StudentProfile;
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
    public Student addStudent(StudentDto studentDto)
    {
        Student student = new Student();
        student.setName(studentDto.name());
        student.setEmail(studentDto.email());
        student.setCourse(studentDto.course());

        StudentProfile profile = new StudentProfile();
        profile.setPhone(studentDto.profile().phone());
        profile.setAddress(studentDto.profile().address());
        profile.setDateOfBirth(studentDto.profile().dateOfBirth());

        student.setProfile(profile);
        return studentRepository.save(student);
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