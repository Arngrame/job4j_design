package ru.job4j.chapter02.task7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisTest {

    @Test
    void firstLogTest(@TempDir Path tempDir) throws IOException {
        String sourcePath = "server1.log";
        String targetPath = "output1.csv";

        File sourceFile = tempDir.resolve(sourcePath).toFile();
        File targetFile = tempDir.resolve(targetPath).toFile();

        try (PrintWriter writer = new PrintWriter(sourceFile)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("300 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02");
        }

        Analysis analysis = new Analysis();
        analysis.unavailable(sourceFile.getAbsolutePath(), targetFile.getAbsolutePath());

        List<String> expectedResult = List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;");

        List<String> actualResult = new ArrayList<>(2);
        try (BufferedReader reader = new BufferedReader(new FileReader(targetFile))) {
            reader.lines().forEach(actualResult::add);
        }

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    void secondLogTest(@TempDir Path tempDir) throws IOException {
        String sourcePath = "server2.log";
        String targetPath = "output2.csv";

        File sourceFile = tempDir.resolve(sourcePath).toFile();
        File targetFile = tempDir.resolve(targetPath).toFile();

        try (PrintWriter writer = new PrintWriter(sourceFile)) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("300 11:02:02");
        }

        Analysis analysis = new Analysis();
        analysis.unavailable(sourceFile.getAbsolutePath(), targetFile.getAbsolutePath());

        List<String> expectedResult = List.of("10:57:01;11:02:02;");

        List<String> actualResult = new ArrayList<>(2);
        try (BufferedReader reader = new BufferedReader(new FileReader(targetFile))) {
            reader.lines().forEach(actualResult::add);
        }

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}