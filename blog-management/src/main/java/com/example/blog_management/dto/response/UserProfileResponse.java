package com.example.blog_management.dto.response;

import java.util.UUID;

public record UserProfileResponse(UUID id, String firstName, String lastName, String phone, String website)
{

}