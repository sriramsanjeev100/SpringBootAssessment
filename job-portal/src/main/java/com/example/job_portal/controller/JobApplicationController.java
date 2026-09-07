package com.example.job_portal.controller;

import com.example.job_portal.dto.request.ApplicationStatusRequest;
import com.example.job_portal.dto.request.JobApplicationRequest;
import com.example.job_portal.dto.response.JobApplicationResponse;
import com.example.job_portal.enums.ApplicationStatus;
import com.example.job_portal.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController
{
    private final JobApplicationService jobApplicationService;
    public JobApplicationController(JobApplicationService jobApplicationService)
    {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> createApplication(@Valid @RequestBody JobApplicationRequest request)
    {
        JobApplicationResponse response = jobApplicationService.createApplication(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<JobApplicationResponse>> getAllApplications()
    {
        return ResponseEntity.ok(jobApplicationService.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getApplicationById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(jobApplicationService.getApplicationById(id));
    }

    @GetMapping("/jobseeker/{jobSeekerId}")
    public ResponseEntity<List<JobApplicationResponse>> getApplicationsByJobSeeker(@PathVariable UUID jobSeekerId)
    {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByJobSeeker(jobSeekerId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationResponse>> getApplicationsByJob(@PathVariable UUID jobId)
    {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByJob(jobId));
    }

    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobApplicationResponse>> getApplicationsByEmployer(@PathVariable UUID employerId)
    {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByEmployer(employerId));
    }

    @GetMapping("/employer/{employerId}/status")
    public ResponseEntity<List<JobApplicationResponse>> getApplicationsByEmployerAndStatus(@PathVariable UUID employerId, @RequestParam ApplicationStatus status)
    {
        return ResponseEntity.ok(jobApplicationService.getApplicationsByEmployerAndStatus(employerId, status));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplicationResponse> updateApplicationStatus(@PathVariable UUID id, @Valid @RequestBody ApplicationStatusRequest request)
    {
        return ResponseEntity.ok(jobApplicationService.updateApplicationStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable UUID id)
    {
        jobApplicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}