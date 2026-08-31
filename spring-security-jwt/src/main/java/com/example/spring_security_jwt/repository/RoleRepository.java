package com.example.spring_security_jwt.repository;

import com.example.spring_security_jwt.entity.Role;
import com.example.spring_security_jwt.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
    Optional<Role> findByName(RoleEnum name);
}