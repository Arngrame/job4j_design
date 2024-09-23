package ru.job4j.chapter01.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> colors = simpleConvert.toList("red", "green", "blue");

        assertThat(colors).hasSize(3)
                .contains("green")
                .contains("blue", Index.atIndex(2))
                .containsAnyOf("cyan", "magenta", "blue")
                .doesNotContain("yellow")
                .doesNotContainNull();
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> colors = simpleConvert.toSet("cyan", "magenta", "yellow", "black");

        assertThat(colors).hasSizeBetween(1, 10)
                .hasSizeGreaterThan(2)
                .hasSizeLessThan(5)
                .hasSizeLessThanOrEqualTo(4)
                .doesNotHaveDuplicates()
                .containsOnlyOnce("cyan", "black")
                .containsSequence("magenta", "yellow");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> smth = simpleConvert.toMap("Java", "Junior", "Middle", "Senior", "Lead", "Technical");

        assertThat(smth).hasSize(6)
                .containsKeys("Java", "Technical")
                .doesNotContainKey("Principle")
                .containsValues(0, 1, 2, 3)
                .containsEntry("Java", 0);
    }
}