package com.mfdev.redditcloneapp.controller;

import com.mfdev.redditcloneapp.dto.user.SignupDto;
import com.mfdev.redditcloneapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@EnableAsync
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDto dto) {
        return authService.signup(dto);
    }

    @GetMapping("/verification/{uuid}")
    public String verifyAccount(@PathVariable UUID uuid) {
        return authService.verifyAccount(uuid);
    }
}
