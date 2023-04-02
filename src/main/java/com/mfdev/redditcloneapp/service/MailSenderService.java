package com.mfdev.redditcloneapp.service;

import com.mfdev.redditcloneapp.dto.user.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender mailSender;

    @Async
    public void sendVerificationMail(SignupDto dto, UUID token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getEmail());
        message.setSubject("Account activation");

        message.setText(generateMessage(dto, token));

        mailSender.send(message);
    }

    private String generateMessage(SignupDto dto, UUID token) {
        String link = String.format("http://localhost:8080/api/v1/auth/verification/%s", token);
        return String.format("Hi, %s! Please follow the link to confirm your account\n%s", dto.getUsername(), link);
    }
}
