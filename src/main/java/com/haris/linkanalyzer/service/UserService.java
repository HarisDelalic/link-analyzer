package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.domain.UserPrincipal;
import com.haris.linkanalyzer.domain.factory.UserFactory;
import com.haris.linkanalyzer.exception.UserNotFoundException;
import com.haris.linkanalyzer.repository.UserRepository;
import com.haris.linkanalyzer.utility.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final JWTTokenProvider jwtTokenProvider;
    private final Authenticator authenticator;

    public User register(User userData) {
        User user = userFactory.createNewUser(userData);

        return userRepository.save(user);
    }

    public User login(User user) {
        authenticate(user);

        return userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword())
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserPrincipal userPrincipal = userRepository.findUserByEmail(email)
                .map(UserPrincipal::new)
                .orElseThrow(UserNotFoundException::new);

        return userPrincipal;
    };

    private void authenticate(User user) {
        authenticator.authenticate(user);
    }

    public String getJwtToken(User user) {
        return jwtTokenProvider.generateJwtToken(new UserPrincipal(user));
    }
}
