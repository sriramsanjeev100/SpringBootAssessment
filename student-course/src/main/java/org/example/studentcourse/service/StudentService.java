package org.example.studentcourse.service;

import org.example.studentcourse.dto.StudentRequestDto;
import org.example.studentcourse.entity.Course;
import org.example.studentcourse.entity.Student;
import org.example.studentcourse.repository.CourseRepository;
import org.example.studentcourse.repository.StudentRepository;
import org.example.studentcourse.response.StudentCourseResponse;
import org.example.studentcourse.response.StudentResponse;
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

    public StudentResponse addStudent(StudentRequestDto dto)
    {
        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setCourse(course);
        Student savedStudent = studentRepository.save(student);
        return mapToResponse(savedStudent);
    }

    public List<StudentResponse> getAllStudents()
    {
        return studentRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public StudentResponse getStudentById(int id)
    {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToResponse(student);
    }

    public StudentCourseResponse getStudentsByCourse(int courseId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<StudentResponse> students = studentRepository.findByCourse(course).stream()
                        .map(this::mapToResponse)
                        .toList();

        return new StudentCourseResponse(course.getId(), course.getCourseName(), students);
    }

    public void deleteStudent(int id)
    {
        studentRepository.deleteById(id);
    }

    private StudentResponse mapToResponse(Student student)
    {
        return new StudentResponse(student.getId(), student.getName(), student.getEmail());
    }
}