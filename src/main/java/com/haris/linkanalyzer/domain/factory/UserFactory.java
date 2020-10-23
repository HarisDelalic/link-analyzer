package com.haris.linkanalyzer.domain.factory;

import com.haris.linkanalyzer.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final BCryptPasswordEncoder passwordEncoder;

    public User createNewUser(User userData) {
        User user = User.builder()
                .username(userData.getUsername())
                .email(userData.getEmail())
                .password(createPassword(userData.getPassword()))
                .build();

        return user;
    }

    private String createPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }
}
