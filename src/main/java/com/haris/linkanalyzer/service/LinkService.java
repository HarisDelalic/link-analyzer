package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.exception.UserNotFoundException;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;

    public Set<Link> getUserLinks(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return linkRepository.findAllByUser(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }
}
