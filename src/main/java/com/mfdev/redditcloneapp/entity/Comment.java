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

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(columnDefinition = "text", length = 2000)
    @NotBlank(message = "Comment cannot be empty")
    private String comment;

    @CreationTimestamp
    private Date dateCreate;

    @ManyToOne
    @JoinColumn(name = "fk__post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "fk__user_id", referencedColumnName = "id")
    private User user;
}
