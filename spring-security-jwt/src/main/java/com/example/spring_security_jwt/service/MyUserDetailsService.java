package com.example.spring_security_jwt.service;

import com.example.spring_security_jwt.entity.Users;
import com.example.spring_security_jwt.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;
    public MyUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Users user = userRepository.findByUsername(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().getName().name())
                .build();
    }
}