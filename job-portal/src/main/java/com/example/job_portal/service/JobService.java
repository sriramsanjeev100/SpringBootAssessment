package com.example.job_portal.service;

import com.example.job_portal.dto.request.JobRequest;
import com.example.job_portal.dto.response.JobResponse;
import com.example.job_portal.entity.Employer;
import com.example.job_portal.entity.Job;
import com.example.job_portal.entity.Skill;
import com.example.job_portal.exception.ResourceNotFoundException;
import com.example.job_portal.repository.EmployerRepository;
import com.example.job_portal.repository.JobRepository;
import com.example.job_portal.repository.SkillRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class JobService
{
    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    private final SkillRepository skillRepository;

    public JobService(JobRepository jobRepository, EmployerRepository employerRepository, SkillRepository skillRepository)
    {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
        this.skillRepository = skillRepository;
    }

    public JobResponse createJob(JobRequest request)
    {
        Employer employer = employerRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found"));

        Job job = new Job();
        setJobFields(job, request, employer);
        job.setPostedDate(LocalDateTime.now());
        Job savedJob = jobRepository.save(job);
        return mapToResponse(savedJob);
    }

    public List<JobResponse> getAllJobs()
    {
        return jobRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public JobResponse getJobById(UUID id)
    {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        return mapToResponse(job);
    }

    public List<JobResponse> getJobsByEmployer(UUID employerId)
    {
        return jobRepository.findByEmployerId(employerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<JobResponse> getJobsByTitle(String title)
    {
        return jobRepository.findByTitle(title)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<JobResponse> getJobsByLocation(String location)
    {
        return jobRepository.findByLocation(location)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<JobResponse> getJobsBySkill(String skill)
    {
        return jobRepository.findDistinctBySkills(skill)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<JobResponse> getRecentJobs()
    {
        return jobRepository.findAllByOrderByPostedDateDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<JobResponse> getFresherJobs()
    {
        return jobRepository.findByExperience(0)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public Page<JobResponse> getJobs(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return jobRepository.findAll(pageable).map(this::mapToResponse);
    }

    public JobResponse updateJob(UUID id, JobRequest request)
    {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        Employer employer = employerRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found"));

        setJobFields(job, request, employer);
        Job updatedJob = jobRepository.save(job);
        return mapToResponse(updatedJob);
    }

    public void deleteJob(UUID id)
    {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        jobRepository.delete(job);
    }

    private JobResponse mapToResponse(Job job)
    {
        List<UUID> skillIds = job.getSkills()
                .stream()
                .map(Skill::getId)
                .toList();

        return new JobResponse
        (
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSalary(),
                job.getLocation(),
                job.getExperience(),
                job.getPostedDate(),
                job.getLastDate(),
                job.getEmployer().getId(),
                skillIds
        );
    }

    private Job setJobFields(Job job, JobRequest request, Employer employer)
    {
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        job.setLocation(request.getLocation());
        job.setExperience(request.getExperience());
        job.setLastDate(request.getLastDate());
        job.setEmployer(employer);

        Set<Skill> skills = new HashSet<>();
        if (request.getSkillIds() != null)
        {
            for (UUID skillId : request.getSkillIds())
            {
                Skill skill = skillRepository.findById(skillId)
                        .orElseThrow(() -> new ResourceNotFoundException("Skill not found: " + skillId));

                skills.add(skill);
            }
        }

        job.setSkills(skills);
        return job;
    }
}