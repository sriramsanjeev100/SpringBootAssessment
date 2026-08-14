package org.example.studentcourse.dto.request;

import java.util.List;

public record CourseRequestDto(String courseName, List<String> skills)
{

}