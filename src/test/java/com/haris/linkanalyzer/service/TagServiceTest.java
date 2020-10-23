package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.Tag;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    LinkRepository linkRepository;

    @Mock
    TagRepository tagRepository;

    @InjectMocks
    TagService tagService;

    @Test
    void create() {
        Link link = Link.builder().id(1L).build();
        Tag toBeSaved = Tag.builder().value("toBeSaved").links(Set.of(link)).build();

        given(linkRepository.findById(1L)).willReturn(Optional.of(link));
        given(tagRepository.save(toBeSaved)).willReturn(toBeSaved);

        tagService.create(link.getId(), toBeSaved);

        then(linkRepository).should(times(1)).findById(link.getId());
        then(tagRepository).should(times(1)).save(toBeSaved);
    }
}