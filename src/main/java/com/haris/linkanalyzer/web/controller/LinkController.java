package com.haris.linkanalyzer.web.controller;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/create")
    public ResponseEntity<Link> create(@Valid @RequestBody Link link) {
        return new ResponseEntity<Link>(linkService.create(link), HttpStatus.CREATED);
    }
}
