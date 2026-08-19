package com.example.blog_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record PostRequest
        (
            @NotBlank
            String title,

            @NotBlank
            String content,

            Integer userId,
            List<Integer> categoryIds
        )
{

}