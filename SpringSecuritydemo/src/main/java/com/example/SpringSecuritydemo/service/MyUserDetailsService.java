package com.example.SpringSecuritydemo.service;

import com.example.SpringSecuritydemo.entity.Users;
import com.example.SpringSecuritydemo.repository.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    private final UserRepo userRepo;
    public MyUserDetailsService(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Users user = userRepo.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}