package com.example.blog_management.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponse
        (
            Integer id,
            String title,
            String content,
            LocalDateTime createdAt,
            Integer userId,
            List<CategoryResponse> categories
        )
{

}