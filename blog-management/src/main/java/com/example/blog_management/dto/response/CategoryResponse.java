package com.example.blog_management.dto.response;

import java.util.UUID;

public record CategoryResponse
        (
                UUID id,
                String name
        )
{

}
