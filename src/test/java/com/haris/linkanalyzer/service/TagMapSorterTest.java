package com.haris.linkanalyzer.service;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TagMapSorterTest {

    @Test
    void sortByNumberOfStringOccurrences() {
        Map<String, Long> occurrencesMap = new HashMap<>();

        occurrencesMap.put("TwoTimes", 2L);
        occurrencesMap.put("OneTime", 1L);
        occurrencesMap.put("FourTimes", 4L);
        occurrencesMap.put("FiveTimes", 5L);
        occurrencesMap.put("ThreeTimes", 3L);

        Map<String, Long> sortedByOccurrences = TagMapSorter.sortByNumberOfStringOccurrences(occurrencesMap);

        List<String> entrySet = new ArrayList<>(sortedByOccurrences.keySet());

        assertEquals("FiveTimes", entrySet.get(0));
        assertEquals("FourTimes", entrySet.get(1));
        assertEquals("ThreeTimes", entrySet.get(2));
        assertEquals("TwoTimes", entrySet.get(3));
        assertEquals("OneTime", entrySet.get(4));
    }
}