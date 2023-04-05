package com.mfdev.redditcloneapp.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateAccountDto {
    private Long userId;
    private String newUsername;
    private String newEmail;
    private String oldPassword;
    private String newPassword;
}
