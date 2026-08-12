package org.example.studentcourse.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;

    @OneToMany(mappedBy = "course")
    private List<Student> students = new ArrayList<>();

    public Course()
    {

    }

    public int getId()
    {
        return id;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public List<Student> getStudents()
    {
        return students;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }
}