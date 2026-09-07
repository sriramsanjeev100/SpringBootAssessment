package com.example.job_portal.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class JobApplicationRequest
{
    @NotNull(message = "Job seeker ID is required")
    private UUID jobSeekerId;

    @NotNull(message = "Job ID is required")
    private UUID jobId;

    public JobApplicationRequest()
    {

    }

    public UUID getJobSeekerId()
    {
        return jobSeekerId;
    }

    public void setJobSeekerId(UUID jobSeekerId)
    {
        this.jobSeekerId = jobSeekerId;
    }

    public UUID getJobId()
    {
        return jobId;
    }

    public void setJobId(UUID jobId)
    {
        this.jobId = jobId;
    }
}