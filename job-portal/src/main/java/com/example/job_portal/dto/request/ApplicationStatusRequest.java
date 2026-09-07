package com.example.job_portal.dto.request;

import com.example.job_portal.enums.ApplicationStatus;
import jakarta.validation.constraints.NotNull;

public class ApplicationStatusRequest
{
    @NotNull(message = "Status is required")
    private ApplicationStatus status;

    public ApplicationStatusRequest()
    {

    }

    public ApplicationStatus getStatus()
    {
        return status;
    }

    public void setStatus(ApplicationStatus status)
    {
        this.status = status;
    }
}