package com.example.job_portal.service;

import com.example.job_portal.entity.RefreshToken;
import com.example.job_portal.entity.User;
import com.example.job_portal.exception.ResourceNotFoundException;
import com.example.job_portal.repository.RefreshTokenRepository;
import com.example.job_portal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService
{
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository)
    {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(UUID userId)
    {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        refreshTokenRepository.deleteByUser(user);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(7));
        refreshToken.setUser(user);
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken)
    {
        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now()))
        {
            refreshTokenRepository.delete(refreshToken);
            throw new IllegalStateException("Refresh token expired");
        }

        return refreshToken;
    }

    public RefreshToken getRefreshToken(String token)
    {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
    }
}