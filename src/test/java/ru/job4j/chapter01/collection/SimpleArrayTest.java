package ru.job4j.chapter01.collection;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl).isEqualTo("first");
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl).isEqualTo("first");
    }

    @Test()
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();

        assertThatThrownBy(() -> array.get(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test()
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");

        assertThatThrownBy(() -> array.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test()
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>();
        Iterator<String> iterator = array.iterator();

        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test()
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>();

        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");

        assertThatThrownBy(it::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }
}