package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LinkSearcher {

    private final LinkRepository linkRepository;

    public Set<Link> searchByTags(Set<String> tagValues) {
        return linkRepository.linksByTags(tagValues);
    }
}
