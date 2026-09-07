package com.example.job_portal.controller;

import com.example.job_portal.dto.request.JobRequest;
import com.example.job_portal.dto.response.JobResponse;
import com.example.job_portal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/jobs")
public class JobController
{
    private final JobService jobService;
    public JobController(JobService jobService)
    {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(@Valid @RequestBody JobRequest request)
    {
        JobResponse response = jobService.createJob(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<JobResponse>> getAllJobs()
    {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobResponse>> getJobsByEmployer(@PathVariable UUID employerId)
    {
        return ResponseEntity.ok(jobService.getJobsByEmployer(employerId));
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<JobResponse>> searchJobsByTitle(@RequestParam String title)
    {
        return ResponseEntity.ok(jobService.getJobsByTitle(title));
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<JobResponse>> searchJobsByLocation(@RequestParam String location)
    {
        return ResponseEntity.ok(jobService.getJobsByLocation(location));
    }

    @GetMapping("/search/skill")
    public ResponseEntity<List<JobResponse>> searchJobsBySkill(@RequestParam String skill)
    {
        return ResponseEntity.ok(jobService.getJobsBySkill(skill));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<JobResponse>> getRecentJobs()
    {
        return ResponseEntity.ok(jobService.getRecentJobs());
    }

    @GetMapping("/fresher")
    public ResponseEntity<List<JobResponse>> getFresherJobs()
    {
        return ResponseEntity.ok(jobService.getFresherJobs());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<JobResponse>> getJobs(@RequestParam int page, @RequestParam int size)
    {
        return ResponseEntity.ok(jobService.getJobs(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(@PathVariable UUID id, @Valid @RequestBody JobRequest request)
    {
        return ResponseEntity.ok(jobService.updateJob(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable UUID id)
    {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}