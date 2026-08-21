package com.example.blog_management.service;

import com.example.blog_management.dto.request.UserRequest;
import com.example.blog_management.dto.response.UserResponse;
import com.example.blog_management.entity.User;
import com.example.blog_management.entity.UserProfile;
import com.example.blog_management.exception.UserNotFoundException;
import com.example.blog_management.exception.UserProfileNotFoundException;
import com.example.blog_management.repository.UserProfileRepository;
import com.example.blog_management.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService
{
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository)
    {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public UserResponse createUser(UserRequest request)
    {
        User user = new User();
        setUserFields(user, request);
        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers()
    {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponse getUser(UUID id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return mapToResponse(user);
    }

    public UserResponse updateUser(UUID id, UserRequest request)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        setUserFields(user, request);
        User updatedUser = userRepository.save(user);
        return mapToResponse(updatedUser);
    }

    public void deleteUser(UUID id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

    private void setUserFields(User user, UserRequest request)
    {
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());
        if (request.profileId() != null)
        {
            UserProfile profile = userProfileRepository.findById(request.profileId())
                    .orElseThrow(() -> new UserProfileNotFoundException("User profile not found with id: " + request.profileId()));

            user.setProfile(profile);
        }
        else
        {
            user.setProfile(null);
        }
    }

    private UserResponse mapToResponse(User user)
    {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail());
    }
}