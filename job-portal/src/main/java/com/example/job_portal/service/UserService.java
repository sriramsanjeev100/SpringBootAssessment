package com.example.job_portal.service;

import com.example.job_portal.dto.request.RegisterRequest;
import com.example.job_portal.dto.response.UserResponse;
import com.example.job_portal.entity.User;
import com.example.job_portal.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public UserResponse registerUser(RegisterRequest request)
    {
        if (userRepository.existsByEmail(request.getEmail()))
        {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        User savedUser = userRepository.save(user);
        emailService.sendWelcomeMail(savedUser);

        return new UserResponse(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole());
    }
}