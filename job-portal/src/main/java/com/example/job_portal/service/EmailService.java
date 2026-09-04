package com.example.job_portal.service;

import com.example.job_portal.entity.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    @Async
    public void sendWelcomeMail(User user)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Welcome to Job Portal");
        message.setText("Hello " + user.getName());
        message.setText("Welcome to Job Portal!");
        message.setText("Your account has been successfully created.");
        mailSender.send(message);
    }
}