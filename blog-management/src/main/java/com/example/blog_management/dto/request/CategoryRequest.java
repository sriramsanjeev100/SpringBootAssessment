package com.example.blog_management.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest
        (
                @NotBlank
                String name
        )
{

}
