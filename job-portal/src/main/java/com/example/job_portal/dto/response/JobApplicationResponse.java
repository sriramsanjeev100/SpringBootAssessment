package com.example.job_portal.dto.response;

import com.example.job_portal.enums.ApplicationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class JobApplicationResponse
{
    private UUID id;
    private UUID jobSeekerId;
    private UUID jobId;
    private LocalDateTime appliedDate;
    private ApplicationStatus status;

    public JobApplicationResponse()
    {

    }

    public JobApplicationResponse(UUID id, UUID jobSeekerId, UUID jobId, LocalDateTime appliedDate, ApplicationStatus status)
    {
        this.id = id;
        this.jobSeekerId = jobSeekerId;
        this.jobId = jobId;
        this.appliedDate = appliedDate;
        this.status = status;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
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

    public LocalDateTime getAppliedDate()
    {
        return appliedDate;
    }

    public void setAppliedDate(LocalDateTime appliedDate)
    {
        this.appliedDate = appliedDate;
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