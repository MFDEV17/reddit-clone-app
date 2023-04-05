package com.mfdev.redditcloneapp.mapper.user;

import com.mfdev.redditcloneapp.dto.user.SignupDto;
import com.mfdev.redditcloneapp.dto.user.UpdateAccountDto;
import com.mfdev.redditcloneapp.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder encoder;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "roles", ignore = true)
    public abstract User signupDtoToUser(SignupDto dto);

    @Mapping(target = "password", source = "newPassword")
    @Mapping(target = "registrationDate", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "username", source = "newUsername")
    @Mapping(target = "email", source = "newEmail")
    public abstract void updateAccountFromUpdateAccountDto(UpdateAccountDto dto, @MappingTarget User user);
}
