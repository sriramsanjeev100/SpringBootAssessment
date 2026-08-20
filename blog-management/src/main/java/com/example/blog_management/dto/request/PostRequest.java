package com.example.blog_management.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record PostRequest
        (
            @NotBlank
            String title,

            @NotBlank
            String content,

            @NotNull
            UUID userId,
            List<UUID> categoryIds
        )
{

}