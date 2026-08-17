package org.example.studentcourse.exception;

public class CourseNotFoundException extends RuntimeException
{
    public CourseNotFoundException(String message)
    {
        super(message);
    }
}
