package com.example.job_portal.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class JobSeekerRequest
{
    @NotNull(message = "User ID is required")
    private UUID userId;

    private Integer experience;

    private String resume;

    public JobSeekerRequest()
    {

    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public Integer getExperience()
    {
        return experience;
    }

    public void setExperience(Integer experience)
    {
        this.experience = experience;
    }

    public String getResume()
    {
        return resume;
    }

    public void setResume(String resume)
    {
        this.resume = resume;
    }
}