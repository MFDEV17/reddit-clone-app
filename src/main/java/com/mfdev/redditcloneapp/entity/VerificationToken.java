package com.mfdev.redditcloneapp.entity;

import com.mfdev.redditcloneapp.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID token = UUID.randomUUID();

    @ManyToOne(cascade = MERGE)
    private User user;

    @Column(nullable = false)
    private Instant expiryDate =
            Instant.now().plus(Duration.ofHours(1L));

    public VerificationToken(User user) {
        this.user = user;
    }
}
