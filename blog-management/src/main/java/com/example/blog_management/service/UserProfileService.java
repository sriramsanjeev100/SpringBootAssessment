package com.example.blog_management.service;

import com.example.blog_management.dto.request.UserProfileRequest;
import com.example.blog_management.dto.response.UserProfileResponse;
import com.example.blog_management.entity.UserProfile;
import com.example.blog_management.exception.UserProfileNotFoundException;
import com.example.blog_management.repository.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserProfileService
{
    private final UserProfileRepository userProfileRepository;
    public UserProfileService(UserProfileRepository userProfileRepository)
    {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileResponse createProfile(UserProfileRequest request)
    {
        UserProfile profile = new UserProfile();
        profile.setFirstName(request.firstName());
        profile.setLastName(request.lastName());
        profile.setPhone(request.phone());
        profile.setWebsite(request.website());
        UserProfile savedProfile = userProfileRepository.save(profile);
        return mapToResponse(savedProfile);
    }

    public List<UserProfileResponse> getAllProfiles()
    {
        return userProfileRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserProfileResponse getProfile(UUID id)
    {
        UserProfile profile = userProfileRepository.findById(id)
                        .orElseThrow(() -> new UserProfileNotFoundException("User profile not found with id: " + id));

        return mapToResponse(profile);
    }

    public UserProfileResponse updateProfile(UUID id, UserProfileRequest request)
    {
        UserProfile profile = userProfileRepository.findById(id)
                        .orElseThrow(() -> new UserProfileNotFoundException("User profile not found with id: " + id));

        profile.setFirstName(request.firstName());
        profile.setLastName(request.lastName());
        profile.setPhone(request.phone());
        profile.setWebsite(request.website());

        UserProfile updatedProfile = userProfileRepository.save(profile);
        return mapToResponse(updatedProfile);
    }

    public void deleteProfile(UUID id)
    {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new UserProfileNotFoundException("User profile not found with id: " + id));

        userProfileRepository.delete(profile);
    }

    private UserProfileResponse mapToResponse(UserProfile profile)
    {
        return new UserProfileResponse(profile.getId(), profile.getFirstName(), profile.getLastName(), profile.getPhone(), profile.getWebsite());
    }
}