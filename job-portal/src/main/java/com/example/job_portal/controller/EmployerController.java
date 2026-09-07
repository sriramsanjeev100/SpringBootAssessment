package com.example.job_portal.controller;

import com.example.job_portal.dto.request.EmployerRequest;
import com.example.job_portal.dto.response.EmployerResponse;
import com.example.job_portal.service.EmployerService;
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
@RequestMapping("/api/employers")
public class EmployerController
{
    private final EmployerService employerService;
    public EmployerController(EmployerService employerService)
    {
        this.employerService = employerService;
    }

    @PostMapping
    public ResponseEntity<EmployerResponse> createEmployer(@Valid @RequestBody EmployerRequest request)
    {
        EmployerResponse response = employerService.createEmployer(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployerResponse>> getAllEmployers()
    {
        return ResponseEntity.ok(employerService.getAllEmployers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployerResponse> getEmployerById(@PathVariable UUID id)
    {
        return ResponseEntity.ok(employerService.getEmployerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployerResponse> updateEmployer(@PathVariable UUID id, @Valid @RequestBody EmployerRequest request)
    {
        return ResponseEntity.ok(employerService.updateEmployer(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable UUID id)
    {
        employerService.deleteEmployer(id);
        return ResponseEntity.noContent().build();
    }
}