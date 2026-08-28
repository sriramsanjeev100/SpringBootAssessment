package com.example.SpringSecuritydemo.repository;

import com.example.SpringSecuritydemo.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer>
{
    Users findByUsername(String username);
}