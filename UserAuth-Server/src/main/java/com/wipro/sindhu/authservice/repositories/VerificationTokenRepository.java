package com.wipro.sindhu.authservice.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sindhu.authservice.dto.Token;

@Repository
public interface VerificationTokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
