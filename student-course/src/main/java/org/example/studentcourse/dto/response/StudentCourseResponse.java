package org.example.studentcourse.dto.response;

import java.util.List;

public record StudentCourseResponse(int courseId, String courseName, List<StudentResponse> students)
{

}