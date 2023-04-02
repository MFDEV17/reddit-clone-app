package com.mfdev.redditcloneapp.repository;

import com.mfdev.redditcloneapp.entity.VerificationToken;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    @EntityGraph(attributePaths = {"user"})
    Optional<VerificationToken> findVerificationTokenByToken(UUID token);
}
