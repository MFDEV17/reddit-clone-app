package com.mfdev.redditcloneapp.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.mfdev.redditcloneapp.entity.user.Role.USER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity(name = "_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Column(nullable = false, length = 80)
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 80)
    private String password;

    @Column(nullable = false)
    private boolean isActive = false;

    @CreationTimestamp
    private Date registrationDate;

    @ElementCollection
    private Set<Role> roles = new HashSet<>(Set.of(USER));
}
