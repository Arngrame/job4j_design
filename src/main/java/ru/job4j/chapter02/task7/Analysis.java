package ru.job4j.chapter02.task7;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class Analysis {

    private static final List<String> UNAVAILABLE_STATES = List.of("400", "500");

    public void unavailable(String source, String target) {
        try (
                BufferedReader sourceFile = new BufferedReader(new FileReader(source));
                PrintWriter targetFile = new PrintWriter(new FileOutputStream(target))
        ) {
            List<StringBuilder> ranges = new ArrayList<>();
            int rangeIndex = 0;

            String currentLine = sourceFile.readLine();
            while (currentLine != null) {
                String[] parts = currentLine.split(" ");

                if (UNAVAILABLE_STATES.contains(parts[0])) {
                    if (ranges.isEmpty()) {
                        ranges.add(new StringBuilder());
                    }

                    if (ranges.get(rangeIndex).isEmpty()) {
                        ranges.get(rangeIndex).append(parts[1]).append(";");
                    }
                } else {
                    if (!ranges.isEmpty()) {
                        ranges.get(rangeIndex).append(parts[1]).append(";");
                        targetFile.println(ranges.get(rangeIndex).toString());

                        rangeIndex++;
                        ranges.add(new StringBuilder());
                    }
                }

                currentLine = sourceFile.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
