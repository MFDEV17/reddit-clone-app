package com.mfdev.redditcloneapp.repository;

import com.mfdev.redditcloneapp.entity.VerificationToken;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    @EntityGraph(attributePaths = {"user"})
    Optional<VerificationToken> findVerificationTokenByToken(UUID token);

    @Query("delete from VerificationToken vt where vt.token = ?1")
    @Modifying
    @Transactional
    void deleteByToken(UUID token);
}
