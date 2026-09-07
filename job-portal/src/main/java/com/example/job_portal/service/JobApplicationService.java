package com.example.job_portal.service;

import com.example.job_portal.dto.request.ApplicationStatusRequest;
import com.example.job_portal.dto.request.JobApplicationRequest;
import com.example.job_portal.dto.response.JobApplicationResponse;
import com.example.job_portal.entity.Job;
import com.example.job_portal.entity.JobApplication;
import com.example.job_portal.entity.JobSeeker;
import com.example.job_portal.enums.ApplicationStatus;
import com.example.job_portal.exception.ResourceNotFoundException;
import com.example.job_portal.repository.JobApplicationRepository;
import com.example.job_portal.repository.JobRepository;
import com.example.job_portal.repository.JobSeekerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class JobApplicationService
{
    private final JobApplicationRepository jobApplicationRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final JobRepository jobRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository, JobSeekerRepository jobSeekerRepository, JobRepository jobRepository)
    {
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobSeekerRepository = jobSeekerRepository;
        this.jobRepository = jobRepository;
    }

    public JobApplicationResponse createApplication(JobApplicationRequest request)
    {
        JobSeeker jobSeeker = jobSeekerRepository.findById(request.getJobSeekerId())
                .orElseThrow(() -> new ResourceNotFoundException("Job seeker not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (LocalDateTime.now().isAfter(job.getLastDate()))
        {
            throw new IllegalStateException("Application deadline has passed");
        }

        JobApplication application = new JobApplication();
        application.setJobSeeker(jobSeeker);
        application.setJob(job);
        application.setAppliedDate(LocalDateTime.now());
        application.setStatus(ApplicationStatus.APPLIED);
        JobApplication savedApplication = jobApplicationRepository.save(application);
        return mapToResponse(savedApplication);
    }

    public List<JobApplicationResponse> getAllApplications()
    {
        return jobApplicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public JobApplicationResponse getApplicationById(UUID id)
    {
        JobApplication application = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found"));

        return mapToResponse(application);
    }

    public List<JobApplicationResponse> getApplicationsByJobSeeker(UUID jobSeekerId)
    {
        return jobApplicationRepository
                .findByJobSeekerId(jobSeekerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<JobApplicationResponse> getApplicationsByJob(UUID jobId)
    {
        return jobApplicationRepository
                .findByJobId(jobId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<JobApplicationResponse> getApplicationsByEmployer(UUID employerId)
    {
        return jobApplicationRepository.findByJob_Employer_Id(employerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<JobApplicationResponse> getApplicationsByEmployerAndStatus(UUID employerId, ApplicationStatus status)
    {
        return jobApplicationRepository
                .findByJob_Employer_IdAndStatus(employerId, status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public JobApplicationResponse updateApplicationStatus(UUID id, ApplicationStatusRequest request)
    {
        JobApplication application = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found"));

        application.setStatus(request.getStatus());
        JobApplication updatedApplication = jobApplicationRepository.save(application);
        return mapToResponse(updatedApplication);
    }

    public void deleteApplication(UUID id)
    {
        JobApplication application = jobApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found"));

        jobApplicationRepository.delete(application);
    }

    private JobApplicationResponse mapToResponse(JobApplication application)
    {
        return new JobApplicationResponse
        (
                application.getId(),
                application.getJobSeeker().getId(),
                application.getJob().getId(),
                application.getAppliedDate(),
                application.getStatus()
        );
    }
}