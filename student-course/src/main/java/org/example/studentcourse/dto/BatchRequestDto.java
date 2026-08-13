package org.example.studentcourse.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record BatchRequestDto(String batchName, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, String mode, int courseId)
{

}