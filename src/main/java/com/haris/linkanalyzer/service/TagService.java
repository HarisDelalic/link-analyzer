package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Tag;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.exception.LinkNotFoundException;
import com.haris.linkanalyzer.exception.UserNotFoundException;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagService {

    private final LinkRepository linkRepository;
    private final TagRepository tagRepository;
    private final LoggedInUserService loggedInUserService;

    public Tag create(Long linkId, Tag tagData) {
        Optional<User> user = getLoggedInUser();

        if(user.isPresent()) {
            return linkRepository.findByIdAndUser(linkId, user.get()).map(
                    foundLink -> {
                        Tag tag = Tag.builder()
                                .value(tagData.getValue())
                                .links(Set.of(foundLink))
                                .build();
                        foundLink.setTags(Set.of(tag));
                        return tagRepository.save(tag);
                        // depending of how much we want to expose,
                        // or link does not exists,
                        // or it belongs to another user so we can't add tags for it
                        // might be better not to expose too much and just throw LinkNotFoundExcpetion
                    }).orElseThrow(LinkNotFoundException::new);
        } else {
            throw new UserNotFoundException();
        }
    };

    private Optional<User> getLoggedInUser() {
        return loggedInUserService.getLoggedInUser();
    }
}
