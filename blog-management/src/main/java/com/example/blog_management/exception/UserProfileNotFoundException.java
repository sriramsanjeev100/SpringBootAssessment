package com.example.blog_management.exception;

public class UserProfileNotFoundException extends ResourceNotFoundException
{
    public UserProfileNotFoundException(String message)
    {
        super(message);
    }
}