package ru.job4j.chapter01.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLastElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        ListUtils.addAfter(input, 1, 3);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfterMiddleElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
        ListUtils.addAfter(input, 1, 3);
        assertThat(Arrays.asList(1, 2, 3, 4, 5, 6), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.addAfter(input, 5, 6);
    }

    @Test
    public void whenRemoveIfEquals() {
        List<String> input = new ArrayList<>(Arrays.asList("New York", "Rome", "Montreal", "Murmansk"));
        ListUtils.removeIf(input, s -> !s.startsWith("M"));
        assertThat(Arrays.asList("Montreal", "Murmansk"), Is.is(input));
    }

    @Test
    public void whenRemoveIfGreaterThan() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
        ListUtils.removeIf(input, integer -> integer > 3);
        assertThat(Arrays.asList(1, 2), Is.is(input));
    }

    @Test
    public void whenReplaceIfEquals() {
        List<String> input = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "WHAT", "Magenta", "Yellow", "Black"));
        ListUtils.replaceIf(input, s -> s.equals("WHAT"),  "Cyan");
        assertThat(Arrays.asList("Red", "Green", "Blue", "Cyan", "Magenta", "Yellow", "Black"), Is.is(input));
    }

    @Test
    public void whenReplaceIfDivisionRemainderIsZero() {
        List<Integer> input = new ArrayList<>(Arrays.asList(100, 205, 300, 405, 500));
        ListUtils.replaceIf(input, new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 100 == 0;
            }
        }, 999);
        assertThat(Arrays.asList(999, 205, 999, 405, 999), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<String> source = new ArrayList<>(Arrays.asList("R", "G", "B"));
        List<String> valuesToDelete = Arrays.asList("G", "B");
        ListUtils.removeAll(source, valuesToDelete);
        assertThat(Collections.singletonList("R"), Is.is(source));
    }

    @Test
    public void whenNothingToRemove() {
        List<String> source = new ArrayList<>(Arrays.asList("R", "G", "B"));
        List<String> valuesToDelete = Arrays.asList("C", "M", "Y", "K");
        ListUtils.removeAll(source, valuesToDelete);
        assertThat(Arrays.asList("R", "G", "B"), Is.is(source));
    }

}