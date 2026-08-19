package com.example.blog_management.repository;

import com.example.blog_management.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>
{

}