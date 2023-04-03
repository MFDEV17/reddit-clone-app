package com.mfdev.redditcloneapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupDto {
    private String username;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "SignupDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=" + "HIDDEN" +
                '}';
    }
}
