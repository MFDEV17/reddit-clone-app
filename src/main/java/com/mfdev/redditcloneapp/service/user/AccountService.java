package com.mfdev.redditcloneapp.service.user;

import com.mfdev.redditcloneapp.dto.user.UpdateAccountDto;
import com.mfdev.redditcloneapp.entity.user.User;
import com.mfdev.redditcloneapp.mapper.user.UserMapper;
import com.mfdev.redditcloneapp.repository.UserRepository;
import com.mfdev.redditcloneapp.util.Util;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final Util util;

    public ResponseEntity<String> updateAccountInfo(UpdateAccountDto dto) {
        User user = userRepository.getReferenceById(dto.getUserId());

        if (dto.getNewPassword() != null && dto.getOldPassword() != null) {
            if (encoder.matches(dto.getOldPassword(), user.getPassword())) {
                dto.setNewPassword(encoder.encode(dto.getNewPassword()));
            } else {
                return ResponseEntity.badRequest().body("Passwords don't matches");
            }
        }

        if (dto.getNewEmail() != null) {
            if (!EmailValidator.getInstance().isValid(dto.getNewEmail())) {
                return ResponseEntity.badRequest().body("Invalid mail address");
            }
        }

        userMapper.updateAccountFromUpdateAccountDto(dto, user);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            return util.onUniqueKeyException(e);
        }

        return ResponseEntity.ok("Account info has updated");
    }
}
