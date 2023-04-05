package com.mfdev.redditcloneapp.mapper.post;

import com.mfdev.redditcloneapp.dto.post.CreatePostDto;
import com.mfdev.redditcloneapp.entity.Post;
import com.mfdev.redditcloneapp.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class PostMapper {

    @Autowired
    protected UserRepository userRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreate", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "user", ignore = true)
    public abstract Post postDtoToPost(CreatePostDto dto);
}
