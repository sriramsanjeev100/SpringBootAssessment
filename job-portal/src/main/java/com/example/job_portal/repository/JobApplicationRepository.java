package com.example.job_portal.repository;

import com.example.job_portal.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobApplicationRepository extends JpaRepository<JobApplication, UUID>
{
    List<JobApplication> findByJobSeekerId(UUID jobSeekerId);
    List<JobApplication> findByJobId(UUID jobId);
}