package com.mfdev.redditcloneapp.mapper.user;

import com.mfdev.redditcloneapp.dto.user.SignupDto;
import com.mfdev.redditcloneapp.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User signupDtoToUser(SignupDto dto);
}
