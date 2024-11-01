package ru.job4j.chapter02.task0;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {

    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            writeIntoFileOutputStream(output, 1, 9);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeIntoFileOutputStream(FileOutputStream output, int start, int end) throws IOException {
        for (int i = start; i < end + 1; i++) {
            for (int j = start; j < end + 1; j++) {
                output.write((i * j + " ").getBytes());
            }
            output.write(System.lineSeparator().getBytes());
        }
    }
}