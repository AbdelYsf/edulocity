package com.abdelysf.edulocity.repository;

import com.abdelysf.edulocity.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVerificationTokenDAO extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}