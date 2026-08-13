package org.example.studentcourse.response;

import java.util.List;

public record CourseResponse(int id, String courseName, List<SkillResponse> skills)
{

}