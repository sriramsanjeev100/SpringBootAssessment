package org.example.studentcourse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;

    @ManyToMany
    @JoinTable(name = "course_skill",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills = new ArrayList<>();

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

    public List<Skill> getSkills()
    {
        return skills;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public void setSkills(List<Skill> skills)
    {
        this.skills = skills;
    }
}