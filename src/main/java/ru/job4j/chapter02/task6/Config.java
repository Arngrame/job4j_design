package ru.job4j.chapter02.task6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            Stream<String> lines = reader.lines();

            for (String line : lines.toList()) {
                if (!(line.startsWith("#") || line.trim().isEmpty())) {

                    String[] split = line.split("=", 2);
                    if (split[0].isEmpty() || split[1].isEmpty()) {
                        throw new IllegalArgumentException("Line '" + line + "' has broken format");
                    }

                    values.put(split[0], split[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, "Key does not exist");
    }

    @Override
    public String toString() {
        values.entrySet().forEach(stringStringEntry -> System.out.println(stringStringEntry));
        return "";
    }

    public static void main(String[] args) {
        Config config = new Config("data/app.properties");
        config.load();
        config.toString();
    }

}