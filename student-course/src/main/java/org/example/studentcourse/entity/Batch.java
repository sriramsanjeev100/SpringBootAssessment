package org.example.studentcourse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Batch
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String batchName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String mode;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Batch()
    {

    }

    public int getId()
    {
        return id;
    }

    public String getBatchName()
    {
        return batchName;
    }

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public LocalTime getStartTime()
    {
        return startTime;
    }

    public LocalTime getEndTime()
    {
        return endTime;
    }

    public String getMode()
    {
        return mode;
    }

    public Course getCourse()
    {
        return course;
    }

    public void setBatchName(String batchName)
    {
        this.batchName = batchName;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    public void setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime)
    {
        this.endTime = endTime;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }
}