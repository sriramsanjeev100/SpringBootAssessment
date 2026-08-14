package org.example.studentcourse.dto.response;

import java.util.List;

public record CourseResponse(int id, String courseName, List<SkillResponse> skills)
{

}