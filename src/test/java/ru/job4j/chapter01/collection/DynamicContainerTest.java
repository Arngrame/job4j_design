package ru.job4j.chapter01.collection;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DynamicContainerTest {
    @Test
    public void whenAddThenGet() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl).isEqualTo("first");
    }

    @Test
    public void whenAddThenIt() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl).isEqualTo("first");
    }

    @Test()
    public void whenGetEmpty() {
        DynamicContainer<String> array = new DynamicContainer<>();
        assertThatThrownBy(() -> array.get(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test()
    public void whenGetOutBound() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");

        assertThatThrownBy(() -> array.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test()
    public void whenGetEmptyFromIt() {
        DynamicContainer<String> array = new DynamicContainer<>();
        assertThatThrownBy(() -> array.iterator().next())
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test()
    public void whenCorruptedIt() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");

        assertThatThrownBy(it::next)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    public void positiveIteratorTest() {
        DynamicContainer<String> array = new DynamicContainer<>();

        array.add("first");
        array.add("second");
        array.add("third");

        Iterator<String> it = array.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo("first");
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo("second");
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo("third");
        assertThat(it.hasNext()).isFalse();
    }
}
