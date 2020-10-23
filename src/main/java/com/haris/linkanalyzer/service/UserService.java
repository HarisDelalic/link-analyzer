package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.exception.UserNotFoundException;
import com.haris.linkanalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public User login(User user) {
        return userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword())
                .orElseThrow(UserNotFoundException::new);
    }
}
