package com.example.blog_management.exception;

public class UserNotFoundException extends ResourceNotFoundException
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}