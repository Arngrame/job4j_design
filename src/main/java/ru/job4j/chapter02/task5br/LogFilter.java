package ru.job4j.chapter02.task5br;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class LogFilter {
    private final String file;

    private static final String FILTER_VALUE = "404";

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> lines = readDataFromFile();
        List<String> filteredLines = new ArrayList<>();

        for (int i = 0; i < lines.size(); ++i) {
            String currentLine = lines.get(i);
            String[] parts = currentLine.split(" ");

            if (parts.length < 2) {
                continue;
            }

            if (FILTER_VALUE.equals(parts[parts.length - 2])) {
                filteredLines.add(currentLine);
            }
        }

        return filteredLines;
    }

    private List<String> readDataFromFile() {
        List<String> lines = null;
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            Stream<String> fileLines = input.lines();
            lines = fileLines == null ? Collections.emptyList() : fileLines.toList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}