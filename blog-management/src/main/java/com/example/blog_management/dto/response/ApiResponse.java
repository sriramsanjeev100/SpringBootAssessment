package com.example.blog_management.dto.response;

import java.time.LocalDateTime;

public record ApiResponse<T>
        (
                LocalDateTime timestamp,
                int status,
                String message,
                T data
        )
{

}