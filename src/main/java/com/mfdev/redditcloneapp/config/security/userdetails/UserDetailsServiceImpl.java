package com.mfdev.redditcloneapp.config.security.userdetails;

import com.mfdev.redditcloneapp.exception.AccountNotFoundException;
import com.mfdev.redditcloneapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(
                repository
                        .findUserByUsername(username)
                        .orElseThrow(() -> {
                            String message = format("Account with username '%s' were not found", username);
                            throw new AccountNotFoundException(message);
                        })
        );
    }
}
