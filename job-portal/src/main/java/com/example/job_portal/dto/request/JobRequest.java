package com.example.job_portal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class JobRequest
{
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private Double salary;

    @NotBlank(message = "Location is required")
    private String location;

    private Integer experience;

    @NotNull(message = "Last date is required")
    private LocalDateTime lastDate;

    @NotNull(message = "Employer ID is required")
    private UUID employerId;

    private List<UUID> skillIds;

    public JobRequest()
    {

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