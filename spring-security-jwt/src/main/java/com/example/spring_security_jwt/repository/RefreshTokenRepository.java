package com.example.spring_security_jwt.repository;

import com.example.spring_security_jwt.entity.RefreshToken;
import com.example.spring_security_jwt.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer>
{
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUser(Users user);
    void deleteByUser(Users user);
}
