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
        message.setText("Hello " + user.getName() + ",\n\n" +"Welcome to Job Portal!\n\n" +"Your account has been successfully created.\n\n" +"Thank you!");
        mailSender.send(message);
    }
}