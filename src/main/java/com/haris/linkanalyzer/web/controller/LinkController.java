package com.haris.linkanalyzer.web.controller;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.Tag;
import com.haris.linkanalyzer.service.LinkService;
import com.haris.linkanalyzer.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;
    private final TagService tagService;

    @GetMapping("/{userId}")
    public ResponseEntity<Set<Link>> getUserLinks(@PathVariable("userId") Long userId) {
        return new ResponseEntity<Set<Link>>(linkService.getUserLinks(userId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Link> create(@Valid @RequestBody Link link) {
        return new ResponseEntity<Link>(linkService.create(link), HttpStatus.CREATED);
    }

    @PostMapping("/{linkId}/tags")
    public ResponseEntity<Tag> createTagForLink(@PathVariable("linkId") Long linkId,
                                                @RequestBody Tag tagData) {
        return new ResponseEntity<Tag>(tagService.create(linkId, tagData), HttpStatus.CREATED);
    }

    @GetMapping("/search/{tagValues}")
    public ResponseEntity<Set<Link>> searchLinksByTags(@PathVariable("tagValues") Set<String> tagValues) {
        return new ResponseEntity<Set<Link>>(linkService.searchLinksByTags(tagValues), HttpStatus.OK);
    }
}
