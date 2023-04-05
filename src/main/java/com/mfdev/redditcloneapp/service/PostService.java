package com.mfdev.redditcloneapp.service;

import com.mfdev.redditcloneapp.dto.post.CreatePostDto;
import com.mfdev.redditcloneapp.mapper.post.PostMapper;
import com.mfdev.redditcloneapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper mapper;

    public ResponseEntity<String> createPost(CreatePostDto dto) {
        try {
            postRepository.save(mapper.postDtoToPost(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Ok");
    }
}
