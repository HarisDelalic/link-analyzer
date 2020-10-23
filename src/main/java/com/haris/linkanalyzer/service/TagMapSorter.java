package com.haris.linkanalyzer.service;

import java.util.*;
import java.util.stream.Collectors;

public class TagMapSorter {

    public static Map<String, Long> sortByNumberOfStringOccurrences(Map<String, Long> map) {
        Map<String, Long> sorted =
                map.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .limit(10)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return sorted;
    }
}
