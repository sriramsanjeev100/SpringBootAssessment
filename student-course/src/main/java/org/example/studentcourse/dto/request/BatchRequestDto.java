package org.example.studentcourse.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.studentcourse.validation.ValidDateRange;

import java.time.LocalDate;
import java.time.LocalTime;

@ValidDateRange
public record BatchRequestDto(

        @NotBlank(message = "Batch name is required")
        String batchName,

        @NotNull(message = "Start date is required")
        @FutureOrPresent(message = "Start date must be today or in the future")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        LocalDate endDate,

        @NotNull(message = "Start time is required")
        LocalTime startTime,

        @NotNull(message = "End time is required")
        LocalTime endTime,

        @NotBlank(message = "Mode is required")
        String mode,

        @NotNull(message = "Course ID is required")
        @Positive(message = "Course ID must be greater than 0")
        Integer courseId)
{

}