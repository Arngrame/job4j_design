package ru.job4j.chapter02.task5bos;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter printWriter = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(out)))) {
            for (String line : data) {
                printWriter.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}