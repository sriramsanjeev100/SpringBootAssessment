package com.example.blog_management.exception;

public class UserProfileNotFoundException extends RuntimeException
{
    public UserProfileNotFoundException(String message)
    {
        super(message);
    }
}