package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.domain.factory.UserFactory;
import com.haris.linkanalyzer.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    private static final String USERNAME = "username";
    private static final String EMAIL = "email@email.com";

    @Mock
    UserFactory userFactory;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username(USERNAME)
                .email(EMAIL)
                .build();
    }

    @Test
    void register() {
        given(userFactory.createNewUser(user)).willReturn(user);
        given(userRepository.save(user)).willReturn(user);

        User registeredUser = userService.register(user);

        then(userRepository).should(times(1)).save(user);

        assertEquals(USERNAME, registeredUser.getUsername());
        assertEquals(EMAIL, registeredUser.getEmail());
    }
}