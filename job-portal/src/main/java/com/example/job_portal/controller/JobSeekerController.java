package com.example.job_portal.controller;

import com.example.job_portal.dto.request.JobSeekerRequest;
import com.example.job_portal.dto.response.JobSeekerResponse;
import com.example.job_portal.service.JobSeekerService;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController
{
    private final JobSeekerService jobSeekerService;
    public JobSeekerController(JobSeekerService jobSeekerService)
    {
        this.jobSeekerService = jobSeekerService;
    }

    @PostMapping
    public ResponseEntity<JobSeekerResponse> createJobSeeker(@Valid @RequestBody JobSeekerRequest request)
    {
        JobSeekerResponse response = jobSeekerService.createJobSeeker(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<JobSeekerResponse>> getAllJobSeekers()
    {
        return ResponseEntity.ok(jobSeekerService.getAllJobSeekers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSeekerResponse> getJobSeekerById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(jobSeekerService.getJobSeekerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSeekerResponse> updateJobSeeker(@PathVariable UUID id, @Valid @RequestBody JobSeekerRequest request)
    {
        return ResponseEntity.ok(jobSeekerService.updateJobSeeker(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobSeeker(@PathVariable UUID id)
    {
        jobSeekerService.deleteJobSeeker(id);
        return ResponseEntity.noContent().build();
    }
}