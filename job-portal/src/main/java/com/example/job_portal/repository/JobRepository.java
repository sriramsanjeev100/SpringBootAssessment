package com.example.job_portal.repository;

import com.example.job_portal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID>
{

}