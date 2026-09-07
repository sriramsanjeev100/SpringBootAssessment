package com.example.job_portal.service;

import com.example.job_portal.dto.request.EmployerRequest;
import com.example.job_portal.dto.response.EmployerResponse;
import com.example.job_portal.entity.Employer;
import com.example.job_portal.entity.User;
import com.example.job_portal.exception.ResourceNotFoundException;
import com.example.job_portal.repository.EmployerRepository;
import com.example.job_portal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployerService
{
    private final EmployerRepository employerRepository;
    private final UserRepository userRepository;

    public EmployerService(EmployerRepository employerRepository, UserRepository userRepository)
    {
        this.employerRepository = employerRepository;
        this.userRepository = userRepository;
    }

    public EmployerResponse createEmployer(EmployerRequest request)
    {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Employer employer = new Employer();
        employer.setCompanyName(request.getCompanyName());
        employer.setLocation(request.getLocation());
        employer.setUser(user);
        Employer savedEmployer = employerRepository.save(employer);
        return mapToResponse(savedEmployer);
    }

    public List<EmployerResponse> getAllEmployers()
    {
        return employerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public EmployerResponse getEmployerById(UUID id)
    {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found"));

        return mapToResponse(employer);
    }

    public EmployerResponse updateEmployer(UUID id, EmployerRequest request)
    {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        employer.setCompanyName(request.getCompanyName());
        employer.setLocation(request.getLocation());
        employer.setUser(user);
        Employer updatedEmployer = employerRepository.save(employer);
        return mapToResponse(updatedEmployer);
    }

    public void deleteEmployer(UUID id)
    {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employer not found"));

        employerRepository.delete(employer);
    }

    private EmployerResponse mapToResponse(Employer employer)
    {
        return new EmployerResponse
        (
                employer.getId(),
                employer.getCompanyName(),
                employer.getLocation(),
                employer.getUser().getId()
        );
    }
}