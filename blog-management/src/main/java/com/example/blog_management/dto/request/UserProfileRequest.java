package com.example.blog_management.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserProfileRequest
        (
        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String phone,

        String website
        )
{

}