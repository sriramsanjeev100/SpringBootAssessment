package com.example.job_portal.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class JobResponse
{
    private UUID id;
    private String title;
    private String description;
    private Double salary;
    private String location;
    private Integer experience;
    private LocalDateTime postedDate;
    private LocalDateTime lastDate;
    private UUID employerId;
    private List<UUID> skillIds;

    public JobResponse()
    {

    }

    public JobResponse(UUID id, String title, String description, Double salary, String location, Integer experience, LocalDateTime postedDate, LocalDateTime lastDate, UUID employerId, List<UUID> skillIds)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.experience = experience;
        this.postedDate = postedDate;
        this.lastDate = lastDate;
        this.employerId = employerId;
        this.skillIds = skillIds;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Double getSalary()
    {
        return salary;
    }

    public void setSalary(Double salary)
    {
        this.salary = salary;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Integer getExperience()
    {
        return experience;
    }

    public void setExperience(Integer experience)
    {
        this.experience = experience;
    }

    public LocalDateTime getPostedDate()
    {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate)
    {
        this.postedDate = postedDate;
    }

    public LocalDateTime getLastDate()
    {
        return lastDate;
    }

    public void setLastDate(LocalDateTime lastDate)
    {
        this.lastDate = lastDate;
    }

    public UUID getEmployerId()
    {
        return employerId;
    }

    public void setEmployerId(UUID employerId)
    {
        this.employerId = employerId;
    }

    public List<UUID> getSkillIds()
    {
        return skillIds;
    }

    public void setSkillIds(List<UUID> skillIds)
    {
        this.skillIds = skillIds;
    }
}