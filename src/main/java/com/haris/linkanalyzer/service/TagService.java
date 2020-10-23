package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Tag;
import com.haris.linkanalyzer.exception.LinkNotFoundException;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService {

    private final LinkRepository linkRepository;
    private final TagRepository tagRepository;

    public Tag create(Long linkId, Tag tagData) {
        return linkRepository.findById(linkId).map(
                foundLink -> {
                    Tag tag = Tag.builder()
                            .value(tagData.getValue())
                            .links(Set.of(foundLink))
                            .build();
                    return tagRepository.save(tag);
                }).orElseThrow(LinkNotFoundException::new);
    };
}
