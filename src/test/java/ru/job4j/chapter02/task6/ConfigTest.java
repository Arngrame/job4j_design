package ru.job4j.chapter02.task6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void simpleLinesWithEmptyDataAndCommentsTest() {
        String path = "./data/config-load-first-case.properties";

        Config config = new Config(path);
        config.load();

        assertThat(config.value("name")).isEqualTo("Ivan");
        assertThat(config.value("lastName")).isEqualTo("Ivanov");
        assertThat(config.value("age")).isEqualTo("27");

        assertThat(config.value("not-existed-key")).isEqualTo("Key does not exist");
    }

    @Test
    void whenLineHasKeyWithoutValueTest() {
        String path = "./data/config-load-second-case.properties";

        Config config = new Config(path);

        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenLineHasValueWithoutKeyTest() {
        String path = "./data/config-load-third-case.properties";

        Config config = new Config(path);

        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenLineHasSeveralSeparatorsTest() {
        String path = "./data/config-load-4th-case.properties";

        Config config = new Config(path);
        config.load();

        assertThat(config.value("key")).isEqualTo("else=====1");
        assertThat(config.value("different")).isEqualTo("true");
    }

    @Test
    void whenLineHasSeveralSeparators2Test() {
        String path = "./data/config-load-5th-case.properties";

        Config config = new Config(path);
        config.load();

        assertThat(config.value("key")).isEqualTo("else=where====");
        assertThat(config.value("another")).isEqualTo("5");
    }

}