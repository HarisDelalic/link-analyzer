package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.Tag;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.TagRepository;
import com.haris.linkanalyzer.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LinkSearcherTest {
    private static final String tag1Value = "tag1";

    @Autowired
    UserRepository userRepository;

    @Autowired
    LinkRepository linkRepository;

    @Autowired
    LinkSearcher linkSearcher;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    LoggedInUserService loggedInUserService;

    User user;
    Link link1;
    Link link2;
    Tag tag1;

    @BeforeEach
    @Transactional
    void setUp() {
        user = User.builder().build();
        userRepository.save(user);

        link1 = Link.builder().value("http://example.com").user(user).build();
        link2 = Link.builder().value("http://example.com").user(user).build();
        linkRepository.saveAll(Arrays.asList(link1, link2));
    }

    @Test
    @Transactional
    void searchUserLinksByTags() {
        tag1 = Tag.builder().links(Set.of(link1)).value(tag1Value).build();
        tagRepository.save(tag1);

        link1.setTags(Set.of(tag1));

        Set<Link> tags = linkSearcher.searchByTags(Set.of(tag1Value));

        assertEquals(1, tags.size());


    }
}
