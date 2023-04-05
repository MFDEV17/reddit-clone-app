package com.mfdev.redditcloneapp.util;

import com.mfdev.redditcloneapp.dto.user.SignupDto;
import com.mfdev.redditcloneapp.entity.user.User;
import com.mfdev.redditcloneapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.substringBetween;

@Service
@RequiredArgsConstructor
public class Util {

    private final UserRepository userRepository;

    public ResponseEntity<String> onUniqueKeyException(DataIntegrityViolationException e) {
        String key = substringBetween(requireNonNull(e.getRootCause()).getMessage(), "Key (", ")");
        String val = substringBetween(requireNonNull(e.getRootCause()).getMessage(), "=(", ")");

        return ResponseEntity.badRequest().body(format("%s '%s' already exists", key, val));
    }

    public String generateSuccessfulMessage(SignupDto dto) {
        return format("You're almost done! \nWe sent a verification mail to '%s'", dto.getEmail());
    }

    public User getSessionUser(Authentication auth) {
        return userRepository
                .findUserByUsername(auth.getName())
                .orElseThrow();
    }
}
