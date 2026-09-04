package com.example.job_portal.repository;

import com.example.job_portal.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployerRepository extends JpaRepository<Employer, UUID>
{

}