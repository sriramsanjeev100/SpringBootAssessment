package com.example.job_portal.dto.response;

import java.util.UUID;

public class EmployerResponse
{
    private UUID id;
    private String companyName;
    private String location;
    private UUID userId;

    public EmployerResponse()
    {

    }

    public EmployerResponse(UUID id, String companyName, String location, UUID userId)
    {
        this.id = id;
        this.companyName = companyName;
        this.location = location;
        this.userId = userId;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }
}