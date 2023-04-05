package com.mfdev.redditcloneapp.aop;

import com.mfdev.redditcloneapp.dto.user.SignupDto;
import com.mfdev.redditcloneapp.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthServiceAOP {
    private final PasswordEncoder encoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @After("execution(* com.mfdev.redditcloneapp.service.user.AuthService.verifyAccount(..))")
    public void deleteToken(JoinPoint jp) {
        log.info("Deleting verification token...");

        verificationTokenRepository.deleteByToken((UUID) jp.getArgs()[0]);

        log.info("Token has been deleted");
    }

    @Before("execution(* com.mfdev.redditcloneapp.service.user.AuthService.signup(..))")
    public void updatePassword(JoinPoint jp) {
        Object arg = jp.getArgs()[0];

        log.info("Encoding password...");

        if (arg instanceof SignupDto dto) {
            dto.setPassword(encoder.encode(dto.getPassword()));
        }

        log.info("Password has been encoded");
    }
}
