package com.example.job_portal.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.FetchType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
public class JobSeeker
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer experience;

    private String resume;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "jobseeker_skills",
            joinColumns = @JoinColumn(name = "jobseeker_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    public JobSeeker()
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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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
