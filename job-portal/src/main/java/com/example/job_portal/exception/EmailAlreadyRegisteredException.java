package com.example.job_portal.exception;

public class EmailAlreadyRegisteredException extends RuntimeException
{
    public EmailAlreadyRegisteredException(String message)
    {
        super(message);
    }
}