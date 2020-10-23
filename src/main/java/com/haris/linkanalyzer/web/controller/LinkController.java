package com.haris.linkanalyzer.web.controller;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/{userId}")
    public ResponseEntity<Set<Link>> getUserLinks(@PathVariable("userId") Long userId) {
        return new ResponseEntity<Set<Link>>(linkService.getUserLinks(userId), HttpStatus.OK);
    }
}
