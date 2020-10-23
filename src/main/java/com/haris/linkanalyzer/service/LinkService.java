package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.exception.UserNotFoundException;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.UserRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Transactional
    public Link create(Link link) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByEmail(userEmail);

        if(user.isPresent()) {
            link.setUser(user.get());
            return linkRepository.save(link);
        } else {
            throw new UserNotFoundException();
        }
    }
}
