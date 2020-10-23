package com.haris.linkanalyzer.web.controller;

import com.haris.linkanalyzer.constant.SecurityConstant;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        User registeredUser = userService.register(user);
        HttpHeaders headers = saveJwtTokenToHeaders(user);
        return new ResponseEntity<>(registeredUser, headers, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User user) {
        User loggedInUser = userService.login(user);
        HttpHeaders headers = saveJwtTokenToHeaders(user);
        return new ResponseEntity<>(loggedInUser, headers, HttpStatus.OK);
    }

    private HttpHeaders saveJwtTokenToHeaders(User user) {
        String jwtToken = userService.getJwtToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(SecurityConstant.JWT_TOKEN_HEADER, jwtToken);
        return headers;
    }
}
