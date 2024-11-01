package ru.job4j.chapter02.task1;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    public static void main(String[] args) {

        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }

            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(formatOutput(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatOutput(String line) {
        boolean result = Integer.parseInt(line) % 2 == 0;
        return line + " is " + (result ? "even number" : "odd number");
    }
}