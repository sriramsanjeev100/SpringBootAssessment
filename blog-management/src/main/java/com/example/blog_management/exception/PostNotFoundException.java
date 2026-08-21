package com.example.blog_management.exception;

public class PostNotFoundException extends ResourceNotFoundException
{
    public PostNotFoundException(String message)
    {
        super(message);
    }
}