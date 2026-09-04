package com.example.job_portal.repository;

import com.example.job_portal.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID>
{

}