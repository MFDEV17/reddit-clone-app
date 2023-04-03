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

    @Before("execution(* com.mfdev.redditcloneapp.service.AuthService.signup(..))")
    public void signup(JoinPoint jp) {
        SignupDto signupDto = (SignupDto) jp.getArgs()[0];

        log.info("Creating new user: {}", signupDto);
        log.info("Encoding user password...");

        signupDto.setPassword(encoder.encode(signupDto.getPassword()));

        log.info("The password has been encoded");
    }

    @After("execution(* com.mfdev.redditcloneapp.service.AuthService.verifyAccount(..))")
    public void deleteToken(JoinPoint jp) {
        log.info("Deleting verification token...");
        verificationTokenRepository.deleteByToken((UUID) jp.getArgs()[0]);
        log.info("Token has been deleted");
    }
}
