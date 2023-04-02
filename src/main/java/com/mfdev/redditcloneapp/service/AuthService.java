package com.mfdev.redditcloneapp.service;

import com.mfdev.redditcloneapp.dto.user.SignupDto;
import com.mfdev.redditcloneapp.entity.VerificationToken;
import com.mfdev.redditcloneapp.entity.user.User;
import com.mfdev.redditcloneapp.mapper.user.UserMapper;
import com.mfdev.redditcloneapp.repository.UserRepository;
import com.mfdev.redditcloneapp.repository.VerificationTokenRepository;
import com.mfdev.redditcloneapp.util.Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Util util;
    private final MailSenderService mailSenderService;
    private final VerificationTokenRepository verificationTokenRepository;

    @PreAuthorize("permitAll()")
    public ResponseEntity<String> signup(SignupDto dto) {
        VerificationToken token;

        try {
            if (EmailValidator.getInstance().isValid(dto.getEmail())) {
                dto.setPassword(encoder.encode(dto.getPassword()));
                token = verificationTokenRepository.save(new VerificationToken(userRepository.save(userMapper.signupDtoToUser(dto))));
                mailSenderService.sendVerificationMail(dto, token.getToken());
            } else {
                return ResponseEntity.badRequest().body("Incorrect mail");
            }
        } catch (DataIntegrityViolationException e) {
            return util.onUniqueKeyException(e);
        } catch (MailException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(util.generateSuccessfulMessage(dto));
    }

    public String verifyAccount(UUID uuid) {
        VerificationToken token = verificationTokenRepository
                .findVerificationTokenByToken(uuid).orElseThrow();
        if (Instant.now().isAfter(token.getExpiryDate())) {
            return "The link has expired";
        } else {
            User user = token.getUser();
            user.setActive(true);
            userRepository.save(user);

            return "Your account has been activated";
        }

    }
}
