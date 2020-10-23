package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoggedInUserService {

    private final UserRepository userRepository;

    public Optional<User> getLoggedInUser() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findUserByEmail(userEmail);
    }
}
