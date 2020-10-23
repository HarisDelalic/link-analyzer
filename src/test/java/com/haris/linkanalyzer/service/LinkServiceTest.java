package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LinkServiceTest {

    @Mock
    LinkRepository linkRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    LinkService linkService;

    User user1;
    User user2;
    Link user1link1;
    Link user1link2;
    Link user2link3;

    @BeforeEach
    void setUp() {
        user1 = User.builder().id(1L).build();
        user2 = User.builder().id(1L).build();

        user1link1 = Link.builder().user(user1).parsedContent("content_user1link1").build();
        user1link2 = Link.builder().user(user1).parsedContent("content_user1link2").build();
        user2link3 = Link.builder().user(user2).parsedContent("content_user2link3").build();
    }

    @Test
    void getUserLinks() {
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        when(linkRepository.findAllByUser(user1)).thenReturn(Set.of(user1link1, user1link2));

        Set<Link> user1Links = linkService.getUserLinks(user1.getId());

        assertEquals(2, user1Links.size());
    }
}