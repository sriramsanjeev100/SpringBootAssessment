package com.example.spring_security_jwt.controller;

import com.example.spring_security_jwt.dto.LoginRequest;
import com.example.spring_security_jwt.dto.LoginResponse;
import com.example.spring_security_jwt.dto.RefreshRequest;
import com.example.spring_security_jwt.entity.RefreshToken;
import com.example.spring_security_jwt.entity.Users;
import com.example.spring_security_jwt.repository.UserRepository;
import com.example.spring_security_jwt.service.JwtService;
import com.example.spring_security_jwt.service.RefreshTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @GetMapping("/user")
    public String user()
    {
        return "Welcome User";
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest user)
    {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(),
                                user.getPassword()
                        )
                );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(userDetails);
        Users dbUser = userRepository.findByUsername(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(dbUser.getId());

        return new LoginResponse(accessToken, refreshToken.getToken());
    }


    @PostMapping("/refresh")
    public LoginResponse refresh(@RequestBody RefreshRequest request)
    {
        RefreshToken refreshToken = refreshTokenService
                .findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        Users user = refreshToken.getUser();
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String newAccessToken = jwtService.generateToken(userDetails);

        return new LoginResponse(newAccessToken, refreshToken.getToken());
    }
}