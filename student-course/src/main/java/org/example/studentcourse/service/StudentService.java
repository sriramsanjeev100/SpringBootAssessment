package org.example.studentcourse.service;

import org.example.studentcourse.dto.StudentRequestDto;
import org.example.studentcourse.entity.Course;
import org.example.studentcourse.entity.Student;
import org.example.studentcourse.repository.CourseRepository;
import org.example.studentcourse.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository)
    {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student addStudent(StudentRequestDto dto)
    {
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setCourse(course);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id)
    {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(int id)
    {
        studentRepository.deleteById(id);
    }
}