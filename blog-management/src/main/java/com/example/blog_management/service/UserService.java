package com.example.blog_management.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository)
    {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public UserResponse createUser(UserRequest request)
    {
        log.info("Creating user with username: {}", request.username());
        User user = new User();
        setUserFields(user, request);
        User savedUser = userRepository.save(user);
        log.info("User created successfully with id: {}", savedUser.getId());
        return mapToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers()
    {
        log.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public UserResponse getUser(UUID id)
    {
        log.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return mapToResponse(user);
    }

    public UserResponse updateUser(UUID id, UserRequest request)
    {
        log.info("Updating user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        setUserFields(user, request);
        log.info("User updated successfully with id: {}", id);
        return mapToResponse(user);
    }

    public void deleteUser(UUID id)
    {
        log.info("Deleting user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
        log.info("User deleted successfully with id: {}", id);
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