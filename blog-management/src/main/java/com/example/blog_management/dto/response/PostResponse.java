package com.example.blog_management.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PostResponse
        (
            UUID id,
            String title,
            String content,
            LocalDateTime createdDate,
            UUID userId,
            List<CategoryResponse> categories
        )
{

}