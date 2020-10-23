package com.haris.linkanalyzer.service;

import com.haris.linkanalyzer.domain.Link;
import com.haris.linkanalyzer.domain.Tag;
import org.apache.commons.collections4.IterableUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class HtmlParserTest {

    @Test
    void parse() throws IOException {
        File in = getFile("/htmltests/retrieve_tags.html");
        Document htmlContent = Jsoup.parse(in, "UTF-8", "http://www.retrieve_tags.com/");
        Link link = Link.builder().value("http://www.retrieve_tags.com/").build();

        HtmlParser parser = new HtmlParser();
        Set<Tag> suggestedTags = parser.parse(htmlContent, link);
        assertEquals("Real", IterableUtils.get(suggestedTags, 0).getValue());
        assertEquals("Barcelona", IterableUtils.get(suggestedTags, 1).getValue());
    }

    public static File getFile(String resourceName) {
        try {
            URL resource = HtmlParserTest.class.getResource(resourceName);
            return resource != null ? new File(resource.toURI()) : new File("/404");
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}