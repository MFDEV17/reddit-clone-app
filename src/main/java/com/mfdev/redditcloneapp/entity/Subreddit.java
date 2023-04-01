package com.mfdev.redditcloneapp.entity;

import com.mfdev.redditcloneapp.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subreddit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Subreddit name cannot be empty")
    private String name;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    private Date dateCreate;

    @OneToMany
    private List<Post> posts;

    @ManyToOne
    @JoinColumn(name = "fk__user_id", referencedColumnName = "id")
    private User creator;
}
