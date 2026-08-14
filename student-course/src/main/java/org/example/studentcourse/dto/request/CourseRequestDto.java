package org.example.studentcourse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CourseRequestDto(

        @NotBlank(message = "Course name is required")
        String courseName,

        @NotEmpty(message = "At least one skill is required")
        List<@NotBlank(message = "Skill name cannot be blank") String> skills
)
{

}