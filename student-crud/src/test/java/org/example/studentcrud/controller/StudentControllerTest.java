package org.example.studentcrud.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.example.studentcrud.dto.StudentDto;
import org.example.studentcrud.dto.StudentProfileDto;
import org.example.studentcrud.entity.Student;
import org.example.studentcrud.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StudentControllerTest
{
    @InjectMocks
    StudentController studentController;

    @Mock
    StudentService studentService;

    StudentDto studentDto;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        StudentProfileDto profileDto = new StudentProfileDto("9562188411", "Palakkad", "20-01-2006");
        studentDto = new StudentDto("Arya", "arya@gmail.com", "MECH", profileDto);
    }

    @Test
    void testGetAllStudents()
    {
        Student student1 = new Student(1, "Ind", "ind@gmail.com", "CSE");
        Student student2 = new Student(2, "Aus", "aus@gmail.com", "ECE");
        List<Student> students = List.of(student1, student2);
        when(studentService.getAllStudents()).thenReturn(students);
        List<Student> result = studentController.getAllStudents();
        assertNotNull(result);
        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals("Ind", result.get(0).getName()),
                () -> assertEquals("Aus", result.get(1).getName())
        );
        verify(studentService).getAllStudents();
    }

    @Test
    void testGetStudentById()
    {
        Student student = new Student(1, "Ind", "ind@gmail.com", "CSE");
        when(studentService.getStudentById(1)).thenReturn(student);
        Student result = studentController.getStudentById(1);
        assertNotNull(result);
        assertAll(
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("Ind", result.getName()),
                () -> assertEquals("ind@gmail.com", result.getEmail()),
                () -> assertEquals("CSE", result.getCourse())
        );
        verify(studentService).getStudentById(1);
    }

    @Test
    void testAddStudent()
    {
        Student savedStudent = new Student(1, "Arya", "arya@gmail.com", "MECH");
        when(studentService.addStudent(any(StudentDto.class))).thenReturn(savedStudent);
        Student result = studentController.addStudent(studentDto);
        assertNotNull(result);
        assertAll(
                () -> assertEquals("Arya", result.getName()),
                () -> assertEquals("arya@gmail.com", result.getEmail()),
                () -> assertEquals("MECH", result.getCourse())
        );
        verify(studentService).addStudent(any(StudentDto.class));
    }

    @Test
    void testUpdateStudent()
    {
        Student updatedStudent = new Student(1, "John", "john@gmail.com", "EEE");
        when(studentService.updateStudent(1, studentDto)).thenReturn(updatedStudent);
        Student result = studentController.updateStudent(1, studentDto);
        assertNotNull(result);
        assertAll(
                () -> assertEquals("John", result.getName()),
                () -> assertEquals("john@gmail.com", result.getEmail()),
                () -> assertEquals("EEE", result.getCourse())
        );
        verify(studentService).updateStudent(1, studentDto);
    }

    @Test
    void testDeleteStudent()
    {
        String result = studentController.deleteStudent(1);
        assertEquals("Student deleted successfully", result);
        verify(studentService).deleteStudent(1);
    }
}
