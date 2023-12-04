package ru.job4j.chapter01.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicContainerTest {
    @Test
    public void whenAddThenGet() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }

    @Test
    public void positiveIteratorTest() {
        DynamicContainer<String> array = new DynamicContainer<>();
        array.add("first");
        array.add("second");
        array.add("third");
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("second"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("third"));
        assertThat(it.hasNext(), is(false));
    }
}
