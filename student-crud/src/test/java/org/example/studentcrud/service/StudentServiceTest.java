package org.example.studentcrud.service;

import org.example.studentcrud.dto.StudentDto;
import org.example.studentcrud.dto.StudentProfileDto;
import org.example.studentcrud.entity.Student;
import org.example.studentcrud.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest
{
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testGetAllStudents()
    {
        Student student1 = new Student(1, "Ind", "ind@gmail.com", "CSE");
        Student student2 = new Student(2, "Aus", "aus@gmail.com", "ECE");
        List<Student> students = List.of(student1, student2);
        when(studentRepository.findAll()).thenReturn(students);
        List<Student> result = studentService.getAllStudents();
        assertEquals(2, result.size());
        verify(studentRepository).findAll();
    }

    @Test
    public void testGetStudentById()
    {
        Student student = new Student(1, "Ind", "ind@gmail.com", "CSE");
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        Student result = studentService.getStudentById(1);
        assertEquals(student, result);
        verify(studentRepository).findById(1);
    }

    @Test
    public void testAddStudent()
    {
        StudentProfileDto profileDto = new StudentProfileDto("9562188411", "Palakkad", "20-01-2006");
        StudentDto studentDto = new StudentDto("Arya", "arya@gmail.com", "MECH", profileDto);
        Student savedStudent = new Student(1, "Arya", "arya@gmail.com", "MECH");
        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);
        Student result = studentService.addStudent(studentDto);
        assertEquals("Arya", result.getName());
        assertEquals("arya@gmail.com", result.getEmail());
        assertEquals("MECH", result.getCourse());
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    public void testUpdateStudent()
    {
        Student existingStudent = new Student(5, "Shibu", "shibu@gmail.com", "CIVIL");
        StudentDto studentDto = new StudentDto("John", "john@gmail.com", "EEE", null);
        when(studentRepository.findById(1)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(existingStudent);
        Student result = studentService.updateStudent(1, studentDto);
        assertEquals("John", result.getName());
        assertEquals("john@gmail.com", result.getEmail());
        assertEquals("EEE", result.getCourse());
        verify(studentRepository).findById(1);
        verify(studentRepository).save(existingStudent);
    }

    @Test
    public void testDeleteStudent()
    {
        studentService.deleteStudent(1);
        verify(studentRepository).deleteById(1);
    }
}