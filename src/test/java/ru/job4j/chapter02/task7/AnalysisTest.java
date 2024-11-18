package ru.job4j.chapter02.task7;

import org.junit.jupiter.api.Test;

class AnalysisTest {

    @Test
    void firstLogTest() {
        String sourcePath = "./data/server1.log";
        String targetPath = "./data/output1.csv";

        Analysis analysis = new Analysis();
        analysis.unavailable(sourcePath, targetPath);
    }

    @Test
    void secondLogTest() {
        String sourcePath = "./data/server2.log";
        String targetPath = "./data/output2.csv";

        Analysis analysis = new Analysis();
        analysis.unavailable(sourcePath, targetPath);
    }
}