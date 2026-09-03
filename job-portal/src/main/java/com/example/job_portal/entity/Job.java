package com.example.job_portal.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Job
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    private Double salary;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer experience;

    @Column(nullable = false)
    private LocalDateTime postedDate;

    @Column(nullable = false)
    private LocalDateTime lastDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    public Job()
    {

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

    public Employer getEmployer()
    {
        return employer;
    }

    public void setEmployer(Employer employer)
    {
        this.employer = employer;
    }

    public Set<Skill> getSkills()
    {
        return skills;
    }

    public void setSkills(Set<Skill> skills)
    {
        this.skills = skills;
    }
}