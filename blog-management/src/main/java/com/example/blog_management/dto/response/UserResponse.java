package com.example.blog_management.dto.response;

import java.util.UUID;

public record UserResponse(UUID id, String username, String email)
{

}