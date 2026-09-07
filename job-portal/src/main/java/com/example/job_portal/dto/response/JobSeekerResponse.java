package com.example.job_portal.dto.response;

import java.util.UUID;

public class JobSeekerResponse
{
    private UUID id;
    private UUID userId;
    private Integer experience;
    private String resume;

    public JobSeekerResponse()
    {

    }

    public JobSeekerResponse(UUID id, UUID userId, Integer experience, String resume)
    {
        this.id = id;
        this.userId = userId;
        this.experience = experience;
        this.resume = resume;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
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