package com.mfdev.redditcloneapp.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePostDto {
    private Long userId;
    private String postName;
    private String url;
    private String content;
}
