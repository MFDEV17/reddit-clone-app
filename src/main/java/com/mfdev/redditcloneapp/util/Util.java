package com.mfdev.redditcloneapp.util;

import com.mfdev.redditcloneapp.dto.user.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.substringBetween;

@Service
@RequiredArgsConstructor
public class Util {

    public ResponseEntity<String> onUniqueKeyException(DataIntegrityViolationException e) {
        String key = substringBetween(requireNonNull(e.getRootCause()).getMessage(), "Key (", ")");
        String val = substringBetween(requireNonNull(e.getRootCause()).getMessage(), "=(", ")");

        return ResponseEntity.badRequest().body(format("%s '%s' already exists", key, val));
    }

    public String generateSuccessfulMessage(SignupDto dto) {
        return format("You're almost done! \nWe sent a verification mail to '%s'", dto.getEmail());
    }
}
