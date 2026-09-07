package com.example.job_portal.service;

import com.example.job_portal.dto.request.JobSeekerRequest;
import com.example.job_portal.dto.response.JobSeekerResponse;
import com.example.job_portal.entity.JobSeeker;
import com.example.job_portal.entity.User;
import com.example.job_portal.exception.ResourceNotFoundException;
import com.example.job_portal.repository.JobSeekerRepository;
import com.example.job_portal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobSeekerService
{
    private final JobSeekerRepository jobSeekerRepository;
    private final UserRepository userRepository;

    public JobSeekerService(JobSeekerRepository jobSeekerRepository, UserRepository userRepository)
    {
        this.jobSeekerRepository = jobSeekerRepository;
        this.userRepository = userRepository;
    }

    public JobSeekerResponse createJobSeeker(JobSeekerRequest request)
    {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setUser(user);
        jobSeeker.setExperience(request.getExperience());
        jobSeeker.setResume(request.getResume());
        JobSeeker savedJobSeeker = jobSeekerRepository.save(jobSeeker);
        return mapToResponse(savedJobSeeker);
    }

    public List<JobSeekerResponse> getAllJobSeekers()
    {
        return jobSeekerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public JobSeekerResponse getJobSeekerById(UUID id)
    {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job seeker not found"));

        return mapToResponse(jobSeeker);
    }

    public JobSeekerResponse updateJobSeeker(UUID id, JobSeekerRequest request)
    {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job seeker not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        jobSeeker.setUser(user);
        jobSeeker.setExperience(request.getExperience());
        jobSeeker.setResume(request.getResume());
        JobSeeker updatedJobSeeker = jobSeekerRepository.save(jobSeeker);
        return mapToResponse(updatedJobSeeker);
    }

    public void deleteJobSeeker(UUID id)
    {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job seeker not found"));

        jobSeekerRepository.delete(jobSeeker);
    }

    private JobSeekerResponse mapToResponse(JobSeeker jobSeeker)
    {
        return new JobSeekerResponse
        (
                jobSeeker.getId(),
                jobSeeker.getUser().getId(),
                jobSeeker.getExperience(),
                jobSeeker.getResume()
        );
    }
}