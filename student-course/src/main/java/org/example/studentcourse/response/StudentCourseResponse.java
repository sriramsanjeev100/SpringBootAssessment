package org.example.studentcourse.response;

import java.util.List;

public record StudentCourseResponse(int courseId, String courseName, List<StudentResponse> students)
{

}