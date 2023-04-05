package com.mfdev.redditcloneapp.config.security;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    @SneakyThrows
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {
        httpSecurity.csrf().disable();
        httpSecurity.cors().disable();

        httpSecurity.httpBasic(withDefaults());
        httpSecurity.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(STATELESS));

        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                .accessDeniedHandler(new BearerTokenAccessDeniedHandler());

        return httpSecurity.build();
    }
}
