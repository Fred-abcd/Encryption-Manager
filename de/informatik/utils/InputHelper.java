package de.informatik.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputHelper {


    public static String findMostSimilarString(String input, List<String> stringList) {
        double maxSimilarity = 0;
        String mostSimilarString = "";

        for (String str : stringList) {
            double similarity = calculateJaccardSimilarity(input, str);
            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                mostSimilarString = str;
            }
        }

        return mostSimilarString;
    }

    private static double calculateJaccardSimilarity(String str1, String str2) {
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : str1.toCharArray()) {
            set1.add(c);
        }

        for (char c : str2.toCharArray()) {
            set2.add(c);
        }

        Set<Character> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<Character> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }
}