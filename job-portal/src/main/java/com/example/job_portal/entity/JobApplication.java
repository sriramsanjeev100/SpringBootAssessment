package com.example.job_portal.entity;

import com.example.job_portal.enums.ApplicationStatus;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class JobApplication
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime appliedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    public JobApplication()
    {

    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public LocalDateTime getAppliedDate()
    {
        return appliedDate;
    }

    public void setAppliedDate(LocalDateTime appliedDate)
    {
        this.appliedDate = appliedDate;
    }

    public ApplicationStatus getStatus()
    {
        return status;
    }

    public void setStatus(ApplicationStatus status)
    {
        this.status = status;
    }

    public JobSeeker getJobSeeker()
    {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker)
    {
        this.jobSeeker = jobSeeker;
    }

    public Job getJob()
    {
        return job;
    }

    public void setJob(Job job)
    {
        this.job = job;
    }
}