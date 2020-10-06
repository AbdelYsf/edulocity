package com.abdelysf.edulocity.service;

import com.abdelysf.edulocity.exceptions.EduLocityException;
import com.abdelysf.edulocity.model.RefreshToken;
import com.abdelysf.edulocity.repository.RefreshTokenDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
/**
 * responsible for creating , deleting and validating refresh tokens
 */
public class RefreshTokenService {

    private final RefreshTokenDao refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new EduLocityException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}