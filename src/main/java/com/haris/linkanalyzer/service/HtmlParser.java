package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.Tag;
import com.haris.linkanalyzer.exception.FaultyLinkException;
import com.haris.linkanalyzer.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HtmlParser {

    private static final String WHITE_SPACE = "\\W+";

    public Document getHtmlContent(Link link) {
        try {
            return Jsoup.connect(link.getValue()).get();
        } catch (IOException exception) {
            throw new FaultyLinkException();
        }
    }

    public Set<Tag> parse(Document htmlContent, Link link) {
        String bodyWithoutHtmlTags = getBodyWithoutHtmlTags(htmlContent);
        String[] tagWords = getTagWords(bodyWithoutHtmlTags);

        Map<String, Long> unsortedOccurrencesMap = getUnsortedOccurrencesMap(tagWords);

        Map<String, Long> sortedByNumberOfStringOccurrences =
                TagMapSorter.sortByNumberOfStringOccurrences(unsortedOccurrencesMap);

//        Since order of inserts is important it is mandatory to use LinkedHashSet
        Set<Tag> suggestedTags = new LinkedHashSet<>();

        for (Map.Entry<String, Long> entry : sortedByNumberOfStringOccurrences.entrySet()) {
            createTag(link, suggestedTags, entry);
        }
        return suggestedTags;
    }


    private Map<String, Long> getUnsortedOccurrencesMap(String[] tagWords) {
        Map<String, Long> counterMap = new HashMap<>();

        Stream.of(tagWords)
                .collect(Collectors.groupingBy(k -> k, () -> counterMap,
                        Collectors.counting()));
        return counterMap;
    }

    private String getBodyWithoutHtmlTags(Document htmlContent) {
        return Jsoup.parse(htmlContent.body().text()).text();
    }

    private String[] getTagWords(String bodyWithoutHtmlTags) {
        return bodyWithoutHtmlTags.split(WHITE_SPACE);
    }

    private void createTag(Link link, Set<Tag> suggestedTags, Map.Entry<String, Long> entry) {
        Tag tag = new Tag();
        tag.setValue(entry.getKey());
        tag.getLinks().add(link);
        suggestedTags.add(tag);
    }
}
