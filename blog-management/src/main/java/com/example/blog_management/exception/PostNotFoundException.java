package com.example.blog_management.exception;

public class PostNotFoundException extends RuntimeException
{
    public PostNotFoundException(String message)
    {
        super(message);
    }
}