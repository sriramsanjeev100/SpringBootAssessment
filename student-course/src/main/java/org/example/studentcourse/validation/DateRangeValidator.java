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
            System.out.println("DTO is null");
            return true;
        }
        if (dto.startDate() == null || dto.endDate() == null)
        {
            System.out.println("Start Date/End Date is null");
            return true;
        }

        System.out.println("Start Date: " + dto.startDate());
        System.out.println("End Date: " + dto.endDate());

        if (dto.endDate().isAfter(dto.startDate()))
        {
            System.out.println("Date is Valid");
            return true;
        }
        System.out.println("Date is not Valid");
        return false;
    }
}
