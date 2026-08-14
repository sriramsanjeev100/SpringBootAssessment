package org.example.studentcourse.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.example.studentcourse.dto.request.BatchRequestDto;
import jakarta.validation.ConstraintValidator;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, BatchRequestDto>
{
    @Override
    public boolean isValid(BatchRequestDto dto, ConstraintValidatorContext context)
    {
        if (dto == null)
        {
            return true;
        }
        if (dto.startDate() == null || dto.endDate() == null)
        {
            return true;
        }

        return dto.endDate().isAfter(dto.startDate());
    }
}
