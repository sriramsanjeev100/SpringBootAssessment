package org.example.studentcourse.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record StudentRequestDto(

        @NotBlank(message = "Student name is required")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Course ID is required")
        @Positive(message = "Course ID must be greater than 0")
        Integer courseId) {
}