package com.example.blog_management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserRequest
        (
            @NotBlank
            String username,

            @NotBlank
            @Email
            String email,

            @NotBlank
            String password,

            UUID profileId
        )
{

}