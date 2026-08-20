package com.example.blog_management.controller;

import com.example.blog_management.dto.request.UserProfileRequest;
import com.example.blog_management.dto.response.ApiResponse;
import com.example.blog_management.dto.response.UserProfileResponse;
import com.example.blog_management.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController
{
    private final UserProfileService userProfileService;
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserProfileResponse>> createProfile(@Valid @RequestBody UserProfileRequest request)
    {
        UserProfileResponse response = userProfileService.createProfile(request);
        ApiResponse<UserProfileResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.CREATED.value(),
                        "Profile created successfully",
                        response
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfileResponse>>> getAllProfiles()
    {
        List<UserProfileResponse> response = userProfileService.getAllProfiles();
        ApiResponse<List<UserProfileResponse>> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Profiles fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getProfile(@PathVariable UUID id)
    {
        UserProfileResponse response = userProfileService.getProfile(id);
        ApiResponse<UserProfileResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Profile fetched successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateProfile(@PathVariable UUID id, @Valid @RequestBody UserProfileRequest request)
    {
        UserProfileResponse response = userProfileService.updateProfile(id, request);
        ApiResponse<UserProfileResponse> apiResponse =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.OK.value(),
                        "Profile updated successfully",
                        response
                );

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProfile(@PathVariable UUID id)
    {
        userProfileService.deleteProfile(id);
        ApiResponse<Void> response =
                new ApiResponse<>(
                        LocalDateTime.now(),
                        HttpStatus.NO_CONTENT.value(),
                        "Profile deleted successfully",
                        null
                );

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}