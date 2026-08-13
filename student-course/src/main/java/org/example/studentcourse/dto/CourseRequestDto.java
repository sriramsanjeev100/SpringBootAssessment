package org.example.studentcourse.dto;

import java.util.List;

public record CourseRequestDto(String courseName, List<String> skills)
{

}