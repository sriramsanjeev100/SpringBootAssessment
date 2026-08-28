package com.example.spring_security_jwt.repository;

import com.example.spring_security_jwt.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>
{
    Users findByUsername(String username);
}