package com.example.job_portal.config;

import com.example.job_portal.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig
{
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter)
    {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login","/api/auth/refresh")
                        .permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/users")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/users/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/users/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/employers")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/api/employers")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/api/employers/**")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.PUT, "/api/employers/**")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.DELETE, "/api/employers/**")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.POST, "/api/jobseekers")
                        .hasRole("JOB_SEEKER")

                        .requestMatchers(HttpMethod.GET, "/api/jobseekers")
                        .hasRole("JOB_SEEKER")

                        .requestMatchers(HttpMethod.GET, "/api/jobseekers/**")
                        .hasRole("JOB_SEEKER")

                        .requestMatchers(HttpMethod.PUT, "/api/jobseekers/**")
                        .hasRole("JOB_SEEKER")

                        .requestMatchers(HttpMethod.DELETE, "/api/jobseekers/**")
                        .hasRole("JOB_SEEKER")

                        .requestMatchers(HttpMethod.POST, "/api/skills")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/skills")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/skills/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/skills/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/skills/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/jobs")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.PUT, "/api/jobs/**")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.DELETE, "/api/jobs/**")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/api/jobs")
                        .hasAnyRole("EMPLOYER", "JOB_SEEKER")

                        .requestMatchers(HttpMethod.GET, "/api/jobs/**")
                        .hasAnyRole("EMPLOYER", "JOB_SEEKER")

                        .requestMatchers(HttpMethod.POST, "/api/applications")
                        .hasRole("JOB_SEEKER")

                        .requestMatchers(HttpMethod.PUT,"/api/applications/*/status")
                        .hasRole("EMPLOYER")

                        .requestMatchers(HttpMethod.GET, "/api/applications")
                        .hasAnyRole("EMPLOYER", "JOB_SEEKER")

                        .requestMatchers(HttpMethod.GET, "/api/applications/**")
                        .hasAnyRole("EMPLOYER", "JOB_SEEKER")

                        .requestMatchers(HttpMethod.DELETE, "/api/applications/**")
                        .hasRole("JOB_SEEKER")

                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }
}
