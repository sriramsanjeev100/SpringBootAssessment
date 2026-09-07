package com.example.job_portal.repository;

import com.example.job_portal.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID>
{
    List<Job> findByEmployerId(UUID employerId);
    Page<Job> findByTitle(String title, Pageable pageable);
    Page<Job> findByLocation(String location, Pageable pageable);
    Page<Job> findDistinctBySkills(String skill, Pageable pageable);
    List<Job> findAllByOrderByPostedDateDesc();
    List<Job> findByExperience(Integer experience);
    Page<Job> findAll(Pageable pageable);
}