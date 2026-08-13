package org.example.studentcourse.response;

import java.time.LocalDate;
import java.time.LocalTime;

public record BatchResponse(int id, String batchName, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String mode, CourseResponse course)
{

}