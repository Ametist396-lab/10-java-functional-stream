package com.example.task04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task04Main {

    private static final Pattern WORD_PATTERN = Pattern.compile("[\\p{L}\\p{Nd}]+");

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            reader.lines()
                    .flatMap(line -> WORD_PATTERN.matcher(line.toLowerCase()).results()
                            .map(m -> m.group()))
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()))
                    .entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed()
                            .thenComparing(Map.Entry.comparingByKey()))
                    .limit(10)
                    .map(Map.Entry::getKey)
                    .forEach(word -> System.out.print(word + " "));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
}