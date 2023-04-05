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
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Post name cannot be empty")
    @Column(length = 130, nullable = false)
    private String postName;

    private String url;

    @Column(columnDefinition = "text", nullable = false)
    @NotBlank(message = "Post content cannot be blank")
    private String content;

    @CreationTimestamp
    private Date dateCreate;

    @ElementCollection
    private List<Vote> votes;

    @OneToMany
    private List<Comment> comments;

    @ManyToOne
    private User user;
}
