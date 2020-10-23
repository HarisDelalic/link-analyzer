package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.Tag;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.exception.LinkNotFoundException;
import com.haris.linkanalyzer.exception.UserNotFoundException;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    LinkRepository linkRepository;

    @Mock
    TagRepository tagRepository;

    @Mock
    LoggedInUserService loggedInUserService;

    @InjectMocks
    TagService tagService;

    User linkOwner;
    User notLinkOwner;
    Link link;
    Tag tagToSave;

    @BeforeEach
    void setUp() {
        linkOwner = User.builder().build();
        notLinkOwner = User.builder().build();
        link = Link.builder().id(1L).user(linkOwner).build();
        tagToSave = Tag.builder().value("toBeSaved").links(Set.of(link)).build();
    }

    @Test
    void whenUserIsLinkOwner_canAddTags() {
        given(loggedInUserService.getLoggedInUser()).willReturn(Optional.of(linkOwner));
        given(linkRepository.findByIdAndUser(1L, linkOwner)).willReturn(Optional.of(link));
        given(tagRepository.save(tagToSave)).willReturn(tagToSave);

        tagService.create(link.getId(), tagToSave);

        then(linkRepository).should(times(1)).findByIdAndUser(link.getId(), linkOwner);
        then(tagRepository).should(times(1)).save(tagToSave);
    }

    @Test
    void whenUserIsNotLinkOwner_cannotAddTags() {
        given(loggedInUserService.getLoggedInUser()).willReturn(Optional.of(notLinkOwner));
        given(linkRepository.findByIdAndUser(1L, notLinkOwner)).willReturn(Optional.empty());

        assertThrows(LinkNotFoundException.class, () -> {
            tagService.create(link.getId(), tagToSave);
        });

        then(linkRepository).should(times(1)).findByIdAndUser(link.getId(), notLinkOwner);
        then(tagRepository).should(times(0)).save(tagToSave);
    }
}