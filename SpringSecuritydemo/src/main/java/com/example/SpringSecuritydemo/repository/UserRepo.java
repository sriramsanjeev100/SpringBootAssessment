package com.example.SpringSecuritydemo.repository;

import org.springframework.security.core.userdetails.User;

public interface UserRepo extends JpaRepository<User, Integer>
{

}
