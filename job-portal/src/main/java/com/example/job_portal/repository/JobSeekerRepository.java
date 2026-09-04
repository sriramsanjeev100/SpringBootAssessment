package com.example.job_portal.repository;

import com.example.job_portal.entity.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, UUID>
{

}