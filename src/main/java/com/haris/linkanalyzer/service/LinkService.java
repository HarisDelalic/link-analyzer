package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.Tag;
import com.haris.linkanalyzer.domain.User;
import com.haris.linkanalyzer.exception.CannotFindAnyTagOnLinkException;
import com.haris.linkanalyzer.exception.UserNotFoundException;
import com.haris.linkanalyzer.repository.LinkRepository;
import com.haris.linkanalyzer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class LinkService {

    private final UserRepository userRepository;
    private final LinkRepository linkRepository;
    private final LoggedInUserService loggedInUserService;
    private final LinkSearcher linkSearcher;
    private final HtmlParser htmlParser;

    public Set<Link> getUserLinks(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return linkRepository.findAllByUser(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    @Transactional
    public Link create(Link link) {
        Optional<User> user = getLoggedInUser();

        if (user.isPresent()) {
            link.setUser(user.get());
            Document htmlContent = htmlParser.getHtmlContent(link);
            Set<Tag> suggestedTags = htmlParser.parse(htmlContent, link);

            if (suggestedTags.size() > 0) {
                return linkRepository.save(link);
            } else {
                throw new CannotFindAnyTagOnLinkException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    public Set<Link> searchLinksByTags(Set<String> tagValues) {

        if(getLoggedInUser().isPresent()) {
            return linkSearcher.searchByTags(tagValues);
        } else {
            throw new UserNotFoundException();
        }
    }

    public Optional<User> getLoggedInUser() {
        return loggedInUserService.getLoggedInUser();
    }
}
